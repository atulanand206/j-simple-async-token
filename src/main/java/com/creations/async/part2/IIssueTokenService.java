package com.creations.async.part2;

import com.creations.async.models.User;
import com.creations.async.models.UserToken;
import java.util.concurrent.CompletableFuture;

public interface IIssueTokenService {
  CompletableFuture<UserToken> issueToken(User user) throws InterruptedException;
}
