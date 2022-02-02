package com.creations.async.exception;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorInfo handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
    log.error("Received UnauthorizedException::{}", ex.getMessage());
    return new ErrorInfo(new Date(), "Unauthorized", ex.getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorInfo handleNullPointerException(NullPointerException ex, WebRequest request) {
    log.error("Received NullPointerException::{}", ex.getMessage());
    return new ErrorInfo(new Date(), "Bad Request", ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorInfo handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    log.error("Received IllegalArgumentException::{}", ex.getMessage());
    return new ErrorInfo(new Date(), "Bad Request", ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorInfo handle(Exception ex, WebRequest request) {
    log.error("Received UnhandledException::{}", ex.getMessage(), ex);
    return new ErrorInfo(new Date(), "Unhandled exception", ex.getMessage());
  }
}
