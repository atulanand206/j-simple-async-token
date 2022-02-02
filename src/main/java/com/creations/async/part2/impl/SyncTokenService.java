package com.creations.async.part2.impl;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.UserToken;
import com.creations.async.part2.IASyncTokenService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SyncTokenService implements IASyncTokenService {

  private final AuthenticationService authenticationService;
  private final IssueTokenService issueTokenService;

  @Async
  @Override
  public CompletableFuture<UserToken> requestToken(Credentials credentials)
      throws UnauthorizedException, ExecutionException, InterruptedException {
    final var user = authenticationService.authenticate(credentials).get();
    final var userTokenFuture = issueTokenService.issueToken(user);
    final var userToken = userTokenFuture.get();
    return CompletableFuture.completedFuture(userToken);
  }
}
