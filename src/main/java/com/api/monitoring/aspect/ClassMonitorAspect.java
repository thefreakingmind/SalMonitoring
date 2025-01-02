package com.api.monitoring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ClassMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(ClassMonitorAspect.class);

    @Pointcut("@within(com.api.monitoring.annotations.ClassMonitor)")
    public void classMonitorPointcut() {}

    @Before("classMonitorPointcut()")
    public void monitorClass() {
        logger.info("Class monitoring started...");
    }
}