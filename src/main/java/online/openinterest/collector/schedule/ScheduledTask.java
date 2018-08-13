package online.openinterest.collector.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import online.openinterest.collector.service.MarketsService;

@Component
public class ScheduledTask {

    private int currentOpenInterest;

    @Autowired
    private MarketsService service;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * 
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void reportCurrentTime() {

        if (currentOpenInterest != service.getOpenInterest()) {
            currentOpenInterest = service.getOpenInterest();
            log.info("" + currentOpenInterest);
        }

    }
}
