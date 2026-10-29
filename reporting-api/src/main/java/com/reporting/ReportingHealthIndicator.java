package com.reporting;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReportingHealthIndicator implements HealthIndicator {
    @Override
    public @Nullable Health health() {

        boolean isHealth=checkHealth();
        if(isHealth){
            return Health.up().withDetail("Application Service","Available").build();
        }else {
            return Health.down().withDetail("Application Service","Unavailable").build();
        }
    }
    public boolean checkHealth() {
        return true;
    }
}
