package com.example.ordermanagement.shared.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private final MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(BaseException.class)
  ResponseEntity<ErrorResponse> handleBase(BaseException ex) {
    return build(ex.getStatus(), ex.getMessageKey(), ex.getArgs());
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class, PropertyReferenceException.class})
  ResponseEntity<ErrorResponse> handleBadParameter(Exception ex) {
    return build(HttpStatus.BAD_REQUEST, "error.invalid_parameter");
  }

  @ExceptionHandler(OptimisticLockingFailureException.class)
  ResponseEntity<ErrorResponse> handleOptimisticLock(OptimisticLockingFailureException ex) {
    return build(HttpStatus.CONFLICT, "error.concurrent_update");
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
    log.warn("Data integrity violation: {}", ex.getMostSpecificCause().getMessage());
    return build(HttpStatus.CONFLICT, "error.data_conflict");
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
    log.error("Unexpected error", ex);
    return build(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal");
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Map<String, String> errors = new LinkedHashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.putIfAbsent(error.getField(), error.getDefaultMessage()));
    ErrorResponse body = new ErrorResponse(status.value(), resolve("error.validation"), errors);
    return ResponseEntity.status(status).headers(headers).body(body);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String message =
        messageSource.getMessage(
            "error.http." + status.value(),
            null,
            resolve("error.request"),
            LocaleContextHolder.getLocale());
    return ResponseEntity.status(status)
        .headers(headers)
        .body(ErrorResponse.of(status.value(), message));
  }

  private ResponseEntity<ErrorResponse> build(HttpStatus status, String key, Object... args) {
    return ResponseEntity.status(status).body(ErrorResponse.of(status.value(), resolve(key, args)));
  }

  private String resolve(String key, Object... args) {
    return messageSource.getMessage(key, args, key, LocaleContextHolder.getLocale());
  }
}
