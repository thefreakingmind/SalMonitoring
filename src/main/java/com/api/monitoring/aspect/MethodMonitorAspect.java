package com.api.monitoring.aspect;

import com.api.monitoring.service.EmailNotificationService;
import com.api.monitoring.service.MonitoringContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MethodMonitorAspect {


    @Autowired
    private MonitoringContext monitoringContext;
    @Autowired
    private EmailNotificationService emailNotificationService;


    private static final Logger logger = LoggerFactory.getLogger(MethodMonitorAspect.class);

    @Pointcut("@annotation(com.api.monitoring.annotations.MethodMonitor)")
    public void methodMonitorPointcut() {}

    @Around("methodMonitorPointcut()")
    public Object monitorMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method {} is being monitored", joinPoint.getSignature().getName());
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception in method {}: {}", joinPoint.getSignature().getName(), e.getMessage());
            emailNotificationService.sendErrorNotification(
                    "Error in method " + joinPoint.getSignature().getName(),
                    "An error occurred: " + e.getMessage()
            );
            monitoringContext.reportException(joinPoint.getSignature().getName(), e);
            throw e;
        } finally {
            long elapsedTime = System.nanoTime() - startTime;
            logger.info("Method {} executed in {} ns", joinPoint.getSignature().getName(), elapsedTime);
            monitoringContext.reportPerformance(joinPoint.getSignature().getName(), elapsedTime);
        }
    }

    @AfterThrowing(pointcut = "methodMonitorPointcut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception thrown in method {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
