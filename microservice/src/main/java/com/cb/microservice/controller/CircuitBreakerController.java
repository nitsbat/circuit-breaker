package com.cb.microservice.controller;

import com.cb.microservice.dataprovider.DataProxy;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

  Logger log = LoggerFactory.getLogger(DataProxy.class);

  private final CircuitBreakerRegistry circuitBreakerRegistry;

  public CircuitBreakerController(CircuitBreakerRegistry circuitBreakerRegistry) {
    this.circuitBreakerRegistry = circuitBreakerRegistry;
  }

  /**
   * It disables the given Circuit Breaker.
   *
   * @param circuitBreakerName
   */
  @PutMapping(value = "/disable/{circuit_breaker}")
  public void disableCircuitBreaker(@PathVariable("circuit_breaker") String circuitBreakerName) {
    try {
      circuitBreakerRegistry.circuitBreaker(circuitBreakerName).transitionToDisabledState();
    } catch (Exception exception) {
      log.error("Circuit Breaker with name '{}' doesn't exist", circuitBreakerName);
      throw new RuntimeException(String.format("%s doesn't exist", circuitBreakerName));
    }
    log.info(" {} circuit breaker is now in disabled state.", circuitBreakerName);
  }

  /**
   * It enables the given Circuit Breaker.
   *
   * @param circuitBreakerName
   */
  @PutMapping(value = "/enable/{circuit_breaker}")
  public void enableCircuitBreaker(@PathVariable("circuit_breaker") String circuitBreakerName) {
    try {
      circuitBreakerRegistry.circuitBreaker(circuitBreakerName).transitionToClosedState();
    } catch (Exception exception) {
      log.error("Circuit Breaker with name '{}' doesn't exist", circuitBreakerName);
      throw new RuntimeException(String.format("%s doesn't exist", circuitBreakerName));
    }
    log.info(" {} circuit breaker is now in closed state.", circuitBreakerName);
  }
}
