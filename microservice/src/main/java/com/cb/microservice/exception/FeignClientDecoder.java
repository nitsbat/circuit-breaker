package com.cb.microservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class FeignClientDecoder implements ErrorDecoder {

  Logger log = LoggerFactory.getLogger(FeignClientDecoder.class);

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String feignClientClassName, Response response) {
    if (response.status() >= HttpStatus.BAD_REQUEST.value()
        && response.status() < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      log.error(
          "FeignClientErrorDecoder response received for feign client {} with response: {}",
          feignClientClassName,
          response);
      return new ExternalServiceException("Bad Response from external services");
    }
    return defaultErrorDecoder.decode(feignClientClassName, response);
  }
}
