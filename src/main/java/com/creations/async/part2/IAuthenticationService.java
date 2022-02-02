package com.creations.async.part2;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.User;
import java.util.concurrent.CompletableFuture;

public interface IAuthenticationService {
  CompletableFuture<User> authenticate(Credentials credentials)
      throws UnauthorizedException, InterruptedException;
}
