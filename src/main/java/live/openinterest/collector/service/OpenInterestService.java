package live.openinterest.collector.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.openinterest.collector.model.OpenInterest;
import live.openinterest.collector.repository.OpenInterestRepository;

@Service
public class OpenInterestService {

    @Autowired
    private OpenInterestRepository repository;

    /**
     * @param amount
     */
    public void saveOpenInterest(float amount) {
        OpenInterest openInterest = new OpenInterest();
        openInterest.setAmount(amount);
        openInterest.setTimestamp(LocalDateTime.now());

        repository.save(openInterest);
    }

    /**
     * @return
     */
    public float getOpenInterest() {
        OpenInterest last = repository.findFirstByOrderByIdDesc();
        return last.getAmount();
    }

}
