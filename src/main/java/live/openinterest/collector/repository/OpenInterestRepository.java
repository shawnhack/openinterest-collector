package live.openinterest.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import live.openinterest.collector.model.OpenInterest;

@Repository
public interface OpenInterestRepository extends JpaRepository<OpenInterest, Integer> {

    public OpenInterest findFirstByOrderByIdDesc();
}
