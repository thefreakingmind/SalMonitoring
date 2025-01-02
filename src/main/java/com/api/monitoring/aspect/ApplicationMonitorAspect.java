package com.api.monitoring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Aspect
@Component
public class ApplicationMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMonitorAspect.class);

    @EventListener(ApplicationReadyEvent.class)
    public void monitorApplicationHealth() {
        logger.info("Application started. Monitoring memory usage...");
        monitorMemoryUsage();
    }

    private void monitorMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        logger.info("Memory used: {} bytes", memoryUsed);
    }
}