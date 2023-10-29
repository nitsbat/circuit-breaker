package com.cb.microservice.exception;

public class ExternalServiceException extends RuntimeException {

  public ExternalServiceException(String message) {
    super(message);
  }
}
