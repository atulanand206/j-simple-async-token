package com.creations.async.exception;

public class UnauthorizedException extends Exception {
  public UnauthorizedException() {
    super("Request unauthorized because credentials are invalid.");
  }
}
