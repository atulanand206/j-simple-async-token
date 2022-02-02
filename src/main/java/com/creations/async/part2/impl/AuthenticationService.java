package com.creations.async.part2.impl;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.User;
import com.creations.async.part2.IAuthenticationService;
import com.creations.async.utils.Delayer;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
  @Override
  public CompletableFuture<User> authenticate(Credentials credentials)
      throws UnauthorizedException, InterruptedException {
    Delayer.delay();
    validate(credentials);
    return CompletableFuture.completedFuture(new User(credentials.getUsername()));
  }

  private void validate(Credentials credentials) throws UnauthorizedException {
    final var password = credentials.getPassword();
    final var actualPassword = credentials.getUsername().toUpperCase();
    if (!password.equals(actualPassword)) {
      throw new UnauthorizedException();
    }
  }
}
