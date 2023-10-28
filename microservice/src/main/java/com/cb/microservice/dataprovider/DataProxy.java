package com.cb.microservice.dataprovider;

import com.cb.microservice.model.People;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "people-api", url = "${data.url}")
public interface DataProxy {
  Logger log = LoggerFactory.getLogger(DataProxy.class);

  @GetMapping
  @Retry(name = "peopleProxyRetry")
  @CircuitBreaker(name = "peopleProxyCircuitBreaker", fallbackMethod = "serviceFallbackMethod")
  ResponseEntity<List<People>> getCurrentUsers();

  default ResponseEntity<List<People>> serviceFallbackMethod(Throwable exception) {
    log.error(
        "Data server is either unavailable or malfunctioned due to {}", exception.getMessage());
    throw new RuntimeException(exception.getMessage());
  }
}
