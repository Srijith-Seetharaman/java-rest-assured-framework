package com.example.api.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;

public class CircuitBreakerManager {

    private static CircuitBreaker circuitBreaker;

    private CircuitBreakerManager() {
    }

    public static synchronized CircuitBreaker getCircuitBreaker() {
        if (circuitBreaker == null) {

            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)
                    .minimumNumberOfCalls(10)
                    .waitDurationInOpenState(Duration.ofSeconds(10))
                    .build();

            CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

            circuitBreaker = circuitBreakerRegistry.circuitBreaker("apiCircuitBreaker");
        }
        return circuitBreaker;
    }
}
