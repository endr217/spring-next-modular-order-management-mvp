package com.example.ordermanagement.shared.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {

  public ConflictException(String messageKey, Object... args) {
    super(messageKey, HttpStatus.CONFLICT, args);
  }
}
