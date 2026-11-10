package com.example.ordermanagement.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

  private final HttpStatus status;
  private final transient Object[] args;

  protected BaseException(String messageKey, HttpStatus status, Object... args) {
    super(messageKey);
    this.status = status;
    this.args = args;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessageKey() {
    return getMessage();
  }

  public Object[] getArgs() {
    return args.clone();
  }
}
