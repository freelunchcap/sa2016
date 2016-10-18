package com.beijunyi.sa2016;

import com.beijunyi.sa2016.assets.AssetsModule;
import com.beijunyi.sa2016.utils.UtilsModule;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new AssetsModule());
    install(new UtilsModule());
  }

}
