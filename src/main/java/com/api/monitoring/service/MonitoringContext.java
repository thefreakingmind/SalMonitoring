package com.api.monitoring.service;

import com.api.monitoring.service.impl.PrometheusMonitoringAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonitoringContext {

    @Autowired
    private PrometheusMonitoringAdapter monitoringAdapter;


    public void reportException(String methodName, Throwable exception) {
        monitoringAdapter.reportException(methodName, exception);
    }

    public void reportPerformance(String methodName, long elapsedTime) {
        monitoringAdapter.reportPerformance(methodName, elapsedTime);
    }

    public void reportMemoryUsage(long memoryUsed) {
        monitoringAdapter.reportMemoryUsage(memoryUsed);
    }
}
