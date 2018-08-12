package online.openinterest.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import online.openinterest.collector.model.Markets;

public interface MarketsRepository extends JpaRepository<Markets, String> {

    /**
     * @return
     */
    @Query(value = "SELECT sum(CAST(sharesOutstanding AS DECIMAL(0,0))) as shares FROM markets", nativeQuery = true)
    public String getOpenInterest();

}
