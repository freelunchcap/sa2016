package com.beijunyi.sa2016;

import com.beijunyi.sa2016.api.ApiModule;
import com.google.inject.AbstractModule;

public class DevModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new ApiModule());
  }
}
