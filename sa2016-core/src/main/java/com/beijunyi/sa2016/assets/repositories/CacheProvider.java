package com.beijunyi.sa2016.assets.repositories;

import javax.inject.Singleton;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public final class CacheProvider {

  private final AtomicReference<Path> root =
      new AtomicReference<>(Path.of(System.getProperty("user.home"), "SA"));

  public Path root() {
    return root.get();
  }
}
