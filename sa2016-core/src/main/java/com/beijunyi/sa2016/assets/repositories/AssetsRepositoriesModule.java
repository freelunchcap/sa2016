package com.beijunyi.sa2016.assets.repositories;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class AssetsRepositoriesModule extends AbstractModule {

  @Provides
  CacheProvider providesCacheProvider() {
    return new CacheProvider();
  }
}
