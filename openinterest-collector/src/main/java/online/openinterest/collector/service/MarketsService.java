package online.openinterest.collector.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.openinterest.collector.repository.MarketsRepository;

@Service
public class MarketsService {

	@Autowired
	private MarketsRepository marketsRepository;

	/**
	 * @return
	 */
	public int getOpenInterest() {
		return (int) NumberUtils.toFloat(marketsRepository.getOpenInterest());
	}

}
