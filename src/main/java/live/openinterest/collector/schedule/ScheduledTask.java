package live.openinterest.collector.schedule;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import live.openinterest.collector.service.MarketsService;
import live.openinterest.collector.service.OpenInterestService;

@Component
public class ScheduledTask {

    private float currentOpenInterest;

    @Autowired
    private MarketsService marketsService;

    @Autowired
    private OpenInterestService service;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        currentOpenInterest = service.getOpenInterest();
        log.info("Staring open interest: " + currentOpenInterest);
    }

    /**
     * 
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void reportCurrentTime() {

        float openInterest = marketsService.getOpenInterest();

        if (currentOpenInterest != openInterest) {
            currentOpenInterest = openInterest;
            log.info("" + currentOpenInterest);
            service.saveOpenInterest(currentOpenInterest);
        }

    }
}
