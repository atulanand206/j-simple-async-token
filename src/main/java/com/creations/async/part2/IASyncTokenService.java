package com.creations.async.part2;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.UserToken;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IASyncTokenService {
  CompletableFuture<UserToken> requestToken(Credentials credentials)
      throws UnauthorizedException, ExecutionException, InterruptedException;
}
