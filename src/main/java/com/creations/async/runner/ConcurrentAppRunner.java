package com.creations.async.runner;

import com.creations.async.models.Credentials;
import com.creations.async.models.UserToken;
import com.creations.async.part2.IASyncTokenService;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConcurrentAppRunner implements CommandLineRunner {
  @Autowired IASyncTokenService syncTokenService;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("started");
    Instant start = Instant.now();
    int count = 100;
    CompletableFuture<UserToken>[] allFutures = new CompletableFuture[count];

    final var credentials = new Credentials("username", "USERNAME");
    for (int i = 0; i < count; i++) {
      allFutures[i] = syncTokenService.requestToken(credentials);
    }
    System.out.println("started");
    CompletableFuture.allOf(allFutures);

    for (int i = 0; i < count; i++) {
      System.out.println("response: " + allFutures[i].get().toString());
    }

    System.out.println("Total time: " + Duration.between(start, Instant.now()).getSeconds());
  }
}
