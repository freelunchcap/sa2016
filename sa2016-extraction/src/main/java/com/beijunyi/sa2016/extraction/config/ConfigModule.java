package com.beijunyi.sa2016.extraction.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ConfigModule extends AbstractModule {

  @Override
  protected void configure() {
    Multibinder.newSetBinder(binder(), ConfigReader.class)
      .addBinding().to(SerializationConfigReader.class);
  }

}
