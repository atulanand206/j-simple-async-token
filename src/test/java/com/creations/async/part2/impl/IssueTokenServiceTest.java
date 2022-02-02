package com.creations.async.part2.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.creations.async.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IssueTokenServiceTest {

  private static IssueTokenService issueTokenService;

  @BeforeAll
  static void setUp() {
    issueTokenService = new IssueTokenService();
  }

  @Test
  void test_issue_token_success() throws Exception {
    final var user = new User("username");
    final var future = issueTokenService.issueToken(user);
    final var userToken = future.get();
    assertTrue(userToken.getUserToken().startsWith(user.getUserId()));
  }
}
