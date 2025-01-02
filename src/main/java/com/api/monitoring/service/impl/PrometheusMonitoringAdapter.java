package com.api.monitoring.service.impl;

import com.api.monitoring.service.MonitoringAdapter;
import io.prometheus.metrics.core.metrics.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PrometheusMonitoringAdapter implements MonitoringAdapter {

    private static final Counter exceptionCounter = Counter.builder()
            .name("application_exceptions_total")
            .help("Total number of exceptions in the application")
            .labelNames("method")
            .register();

    private static final Gauge methodDurationGauge = Gauge.builder()
            .name("method_duration_seconds")
            .help("Duration of method execution in seconds")
            .labelNames("method")
            .register();

    private static final Gauge memoryUsageGauge = Gauge.builder()
            .name("memory_usage_bytes")
            .help("Memory usage of the application in bytes")
            .register();



    @Override
    public void reportException(String methodName, Throwable exception) {
        exceptionCounter.labelValues(methodName).inc();



    }

    @Override
    public void reportPerformance(String methodName, long elapsedTime) {
        methodDurationGauge.labelValues(methodName).set(elapsedTime / 1_000_000_000.0);


    }

    @Override
    public void reportMemoryUsage(long memoryUsed) {
        memoryUsageGauge.set(memoryUsed);

    }
}
