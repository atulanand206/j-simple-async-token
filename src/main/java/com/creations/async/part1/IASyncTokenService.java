package com.creations.async.part1;

import com.creations.async.models.Credentials;
import com.creations.async.models.User;
import com.creations.async.models.UserToken;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface IASyncTokenService {
  Future<User> authenticate(Credentials credentials);

  Future<UserToken> issueToken(User user);

  default Future<UserToken> requestToken(Credentials credentials)
      throws ExecutionException, InterruptedException {
    return issueToken(authenticate(credentials).get());
  }
}
