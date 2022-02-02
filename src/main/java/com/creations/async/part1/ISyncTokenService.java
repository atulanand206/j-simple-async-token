package com.creations.async.part1;

import com.creations.async.models.Credentials;
import com.creations.async.models.User;
import com.creations.async.models.UserToken;

public interface ISyncTokenService {
  User authenticate(Credentials credentials);

  UserToken issueToken(User user);

  default UserToken requestToken(Credentials credentials) {
    return issueToken(authenticate(credentials));
  }
}
