package live.openinterest.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import live.openinterest.collector.model.DailyCandle;

@Repository
public interface DailyCandleRepository extends JpaRepository<DailyCandle, Integer> {

	/**
	 * @return
	 */
	public DailyCandle findFirstByOrderByIdDesc();
}
