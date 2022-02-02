package com.creations.async.part2.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AuthenticationServiceTest {

  private static AuthenticationService authenticationService;

  @BeforeAll
  static void setUp() {
    authenticationService = new AuthenticationService();
  }

  @Test
  void test_authentication_fail() {
    final var credentials = new Credentials("username", "password");
    assertThrows(
        UnauthorizedException.class, () -> authenticationService.authenticate(credentials));
  }

  @Test
  void test_authentication_success() throws Exception {
    final var credentials = new Credentials("username", "USERNAME");
    final var future = authenticationService.authenticate(credentials);
    final var user = future.get();
    assertEquals(credentials.getUsername(), user.getUserId());
  }
}
