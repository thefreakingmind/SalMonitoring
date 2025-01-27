# SalMonitoring

SalMonitoring is a comprehensive monitoring tool designed to provide in-depth insights into the performance and behavior of your Spring Boot applications. It supports monitoring at various levels: Class-Level, Method-Level, and Application-Level. By tracking key system metrics like CPU usage, memory consumption, heap space, and Java exceptions, SalMonitoring helps optimize your application's performance with real-time visibility.

## Key Features

### 1. Class-Level Monitoring
- **Track Performance at the Class Level**: Monitor specific classes within your Spring Boot application.
- **Method Execution Tracking**: Track the execution of methods within classes for a detailed performance picture.
- **Detailed Logs**: Logs provide insights into method execution times, memory usage, and CPU utilization.

### 2. Method-Level Monitoring
- **Real-Time Method Tracking**: Monitor method execution in real-time to identify performance bottlenecks.
- **Method-Specific Metrics**: Track execution time, number of invocations, memory used, and exceptions.
- **Exception Handling**: Logs exceptions thrown within methods for better error tracking and debugging.

### 3. Application-Level Monitoring
- **Comprehensive System Metrics**: Track overall system performance, including CPU usage, memory usage, heap space, and garbage collection events.
- **Heap Memory Usage**: Monitor heap memory consumption and garbage collection behavior.
- **CPU Usage Monitoring**: Log CPU usage to identify processes consuming excessive resources.
- **Real-Time Exception Logging**: Capture and log Java exceptions thrown during the application's lifecycle.

## Supported Metrics
- **CPU Usage**: Track the amount of CPU used by your application.
- **Memory Usage**: Log memory consumption, including both heap and non-heap memory.
- **Heap Memory Usage**: Monitor heap memory consumption and garbage collection times.
- **Java Exceptions**: Logs details of all exceptions thrown, including stack traces.
- **Thread Utilization**: Monitor thread counts and CPU usage to detect thread contention.
- **Garbage Collection**: Log GC times and memory reclamation activities.

## Installation

### Gradle

Add the following dependency to your `build.gradle`:

```gradle
implementation 'com.salmonitoring:salmonitoring-springboot:1.0.0'

## Configuration

To configure SalMonitoring, modify your `application.properties` or `application.yml` file.

### Example (`application.properties`):

```properties
salmonitoring.enabled=true
salmonitoring.log.level=INFO
salmonitoring.monitoring.level=METHOD
salmonitoring.cpu.threshold=80
salmonitoring.memory.threshold=75

