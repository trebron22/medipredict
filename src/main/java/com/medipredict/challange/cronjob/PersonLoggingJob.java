package com.medipredict.challange.cronjob;

import com.medipredict.challange.model.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PersonLoggingJob {

    private static final Logger logger = LogManager.getLogger(PersonLoggingJob.class);

    @Autowired
    private PersonRepository clientRepository;

    @Scheduled(cron = "0 0 8 * * ?")
    public void logNumberOfClients() {
        long numberOfClients = clientRepository.count();
        logger.info("Number of clients in the system: {}", numberOfClients);
    }
}
