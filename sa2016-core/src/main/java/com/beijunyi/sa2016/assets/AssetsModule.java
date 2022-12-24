package com.beijunyi.sa2016.assets;

import com.beijunyi.sa2016.assets.repositories.AssetsRepositoriesModule;
import com.google.inject.AbstractModule;

public class AssetsModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new AssetsRepositoriesModule());
  }

}
