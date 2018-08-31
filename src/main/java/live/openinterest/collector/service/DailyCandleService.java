package live.openinterest.collector.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.openinterest.collector.model.DailyCandle;
import live.openinterest.collector.model.OpenInterest;
import live.openinterest.collector.repository.DailyCandleRepository;

@Service
public class DailyCandleService {

	private static final Logger log = LoggerFactory.getLogger(DailyCandleService.class);

	@Autowired
	private DailyCandleRepository repository;

	/**
	 * 
	 */
	public void saveCandlestickData(List<OpenInterest> ticks) {

		List<DailyCandle> candlesticks = buildCandlesticks(ticks);

		updateData(candlesticks);

		DailyCandle lastCandle = repository.findFirstByOrderByIdDesc();

		if (lastCandle != null) {
			log.info("Last day: {}", lastCandle.getDate());

			List<DailyCandle> newCandles = candlesticks.stream().filter(c -> c.getDate().isAfter(lastCandle.getDate()))
					.collect(Collectors.toList());
			candlesticks = newCandles;
		}

		repository.saveAll(candlesticks);
	}

	/**
	 * @param ticks
	 */
	private List<DailyCandle> buildCandlesticks(List<OpenInterest> ticks) {

		List<DailyCandle> candlesticks = new ArrayList<DailyCandle>();

		DailyCandle currentCandlestick = new DailyCandle();

		for (OpenInterest tick : ticks) {
			LocalDateTime timestamp = tick.getTimestamp();
			float amount = tick.getAmount();

			LocalDate candleDate = timestamp.toLocalDate();

			if (!candleDate.equals(currentCandlestick.getDate())) {
				currentCandlestick = handleNewCandle(candlesticks, currentCandlestick, candleDate);
			}

			currentCandlestick.update(amount);
		}

		candlesticks.add(currentCandlestick);

		return candlesticks;
	}

	/**
	 * @param candlesticks
	 * @param currentCandlestick
	 * @return
	 */
	private DailyCandle getNextCandle(float open, DailyCandle currentCandlestick, LocalDate candleDate) {

		DailyCandle nextCandlestick = new DailyCandle();
		nextCandlestick.update(open);
		nextCandlestick.setDate(candleDate);

		return nextCandlestick;
	}

	/**
	 * @param candlesticks
	 * @param currentCandlestick
	 * @param candleDate
	 * @return
	 */
	private DailyCandle handleNewCandle(List<DailyCandle> candlesticks, DailyCandle currentCandlestick,
			LocalDate candleDate) {

		float close = 0;

		// Previous completed candlestick
		if (currentCandlestick.getDate() != null) {
			close = handlePreviousCandle(candlesticks, currentCandlestick);
		}

		currentCandlestick = getNextCandle(close, currentCandlestick, candleDate);

		return currentCandlestick;
	}

	/**
	 * @param candlesticks
	 * @param currentCandlestick
	 * @return
	 */
	private float handlePreviousCandle(List<DailyCandle> candlesticks, DailyCandle currentCandlestick) {
		float close = currentCandlestick.getClose();
		candlesticks.add(currentCandlestick);
		return close;
	}

	/**
	 * @param dailyCandle
	 * @param newCandle
	 */
	private boolean updateCandle(DailyCandle dailyCandle, DailyCandle newCandle) {

		boolean updated = false;

		float newClose = newCandle.getClose();

		if (newClose != dailyCandle.getClose()) {
			log.info("Different Close: {}", newClose);
			dailyCandle.setClose(newClose);
			updated = true;
		}

		float newHigh = newCandle.getHigh();

		if (newHigh != dailyCandle.getHigh()) {
			log.info("Different High: {}", newHigh);
			dailyCandle.setHigh(newHigh);
			updated = true;
		}

		float newLow = dailyCandle.getLow();

		if (newCandle.getLow() != newLow) {
			log.info("Different Low: {}", newCandle.getLow());
			dailyCandle.setLow(newCandle.getLow());
			updated = true;
		}

		return updated;
	}

	/**
	 * @param candlesticks
	 */
	private void updateData(List<DailyCandle> candlesticks) {

		List<DailyCandle> savedCandles = repository.findAll();

		Map<LocalDate, DailyCandle> candleMap = candlesticks.stream()
				.collect(Collectors.toMap(c -> c.getDate(), c -> c));

		for (DailyCandle dailyCandle : savedCandles) {
			LocalDate date = dailyCandle.getDate();

			DailyCandle newCandle = candleMap.get(date);

			boolean updated = updateCandle(dailyCandle, newCandle);

			if (updated) {
				repository.save(dailyCandle);
			}
		}
	}

}
