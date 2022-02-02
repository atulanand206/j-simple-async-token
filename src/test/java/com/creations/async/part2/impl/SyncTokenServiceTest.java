package com.creations.async.part2.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SyncTokenServiceTest {

  private static SyncTokenService syncTokenService;

  @BeforeAll
  public static void init() {
    syncTokenService = new SyncTokenService(new AuthenticationService(), new IssueTokenService());
  }

  @Test
  void test_authentication_fail() {
    final var credentials = new Credentials("username", "password");
    assertThrows(UnauthorizedException.class, () -> syncTokenService.requestToken(credentials));
  }

  @Test
  void test_authentication_success() throws Exception {
    final var credentials = new Credentials("username", "USERNAME");
    final var future = syncTokenService.requestToken(credentials);
    final var actualUserToken = future.get();
    assertTrue(actualUserToken.getUserToken().startsWith("username"));
  }
}
