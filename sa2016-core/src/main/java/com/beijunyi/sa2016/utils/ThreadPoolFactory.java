package com.beijunyi.sa2016.utils;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class ThreadPoolFactory {

  @Nonnull
  public static ExecutorService getInstance() {
    return Holder.INSTANCE;
  }

  private static class Holder {
    private static final ExecutorService INSTANCE = createInstance();

    @Nonnull
    private static ExecutorService createInstance() {
      return newFixedThreadPool(getRuntime().availableProcessors());
    }

  }

}
