package com.creations.async.part2.impl;

import com.creations.async.models.User;
import com.creations.async.models.UserToken;
import com.creations.async.part2.IIssueTokenService;
import com.creations.async.utils.Delayer;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class IssueTokenService implements IIssueTokenService {
  @Override
  public CompletableFuture<UserToken> issueToken(User user) throws InterruptedException {
    Delayer.delay();
    final var userId = user.getUserId();
    final var currentTime = new Date();
    final var token = StringUtils.join(userId, "_", currentTime);
    return CompletableFuture.completedFuture(new UserToken(token));
  }
}
