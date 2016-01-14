package com.beijunyi.sa2016.extraction.cmd;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class CommandModule extends AbstractModule {

  @Override
  protected void configure() {
    Multibinder.newSetBinder(binder(), CommandHandler.class)
      .addBinding().to(SpritesExtractionHandler.class);
  }

}
