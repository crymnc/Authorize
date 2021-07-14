package com.experiment.authorize.util;

import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Timer.class);

    public void log() throws InterruptedException {
        while(true) {
            logger.info("Inside scheduleTask - Sending logs to Kafka at " + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
            logger.error("test error");
            logger.warn("test warn");
            logger.debug("test debug");
            Thread.sleep(3000);
        }
    }
}
