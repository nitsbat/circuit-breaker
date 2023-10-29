package com.cb.microservice.config;

import com.cb.microservice.exception.FeignClientDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigs {
  @Bean
  public ErrorDecoder myErrorDecoder() {
    return new FeignClientDecoder();
  }
}
