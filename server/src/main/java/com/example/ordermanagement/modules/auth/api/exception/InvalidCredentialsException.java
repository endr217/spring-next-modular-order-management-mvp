package com.example.ordermanagement.modules.auth.api.exception;

import com.example.ordermanagement.shared.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {

  public InvalidCredentialsException() {
    super("error.invalid_credentials", HttpStatus.UNAUTHORIZED);
  }
}
