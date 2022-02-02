package com.creations.async.utils;

import java.util.Random;

public class Delayer {
  public static void delay() throws InterruptedException {
    Thread.sleep(1 + new Random().nextInt(5000));
  }
}
