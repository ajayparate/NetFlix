package com.netflix.clone.notification_service.service;

// import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;


@Service
public class EmailMetricsServices<MeterRegistry> {

     private final MeterRegistry meterRegistry;
    
    public EmailMetricsServices(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    
    public void recordSuccess(String recipient, String subject) {
        meterRegistry.counter("notification.success",
                "type", "email",
                "recipient", recipient,
                "subject", subject).increment();
    }
    
    public void recordFailure(String recipient, String subject, String error) {
        meterRegistry.counter("notification.failure",
                "type", "email",
                "recipient", recipient,
                "subject", subject,
                "error", error).increment();
    }
}
