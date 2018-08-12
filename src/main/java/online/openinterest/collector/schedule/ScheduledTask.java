package online.openinterest.collector.schedule;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import online.openinterest.collector.service.MarketsService;

@Component
public class ScheduledTask {

    @Autowired
    private MarketsService service;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void reportCurrentTime() {
        // log.info("The time is now {}", dateFormat.format(new Date()));

        log.info("" + service.getOpenInterest());
    }
}
