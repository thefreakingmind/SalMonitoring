package com.api.monitoring.service;

public interface MonitoringAdapter {
    void reportException(String methodName, Throwable exception);
    void reportPerformance(String methodName, long elapsedTime);
    void reportMemoryUsage(long memoryUsed);
}
