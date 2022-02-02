package com.creations.async;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class AsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncApplication.class, args);
  }

  @Bean
  public Executor executor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(8);
    executor.setMaxPoolSize(8);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("RequestToken-");
    executor.initialize();
    return executor;
  }
}
