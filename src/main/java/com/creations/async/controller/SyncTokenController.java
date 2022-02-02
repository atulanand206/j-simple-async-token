package com.creations.async.controller;

import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.UserToken;
import com.creations.async.part2.IASyncTokenService;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncTokenController {
  @Autowired IASyncTokenService syncTokenService;

  @PostMapping(
      value = "/requestToken",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserToken> requestToken(@RequestBody Credentials credentials)
      throws UnauthorizedException, ExecutionException, InterruptedException {
    Preconditions.checkNotNull(credentials, "Credentials must be present");
    Preconditions.checkNotNull(credentials.getUsername(), "Username must be present");
    Preconditions.checkArgument(
        credentials.getUsername().length() > 0, "Username must not be empty");
    Preconditions.checkNotNull(credentials.getPassword(), "Password must be present");
    Preconditions.checkArgument(
        credentials.getPassword().length() > 0, "Password must not be empty");
    return ResponseEntity.ok(syncTokenService.requestToken(credentials).get());
  }
}
