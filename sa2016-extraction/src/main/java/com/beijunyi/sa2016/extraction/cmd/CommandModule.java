package com.beijunyi.sa2016.extraction.cmd;

import java.util.Arrays;
import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class CommandModule extends AbstractModule {

  private static final Collection<Class<? extends CommandHandler>> HANDLERS = Arrays.asList(
    CountHandler.class,
    ExtractHandler.class
  );

  @Override
  protected void configure() {
    Multibinder<CommandHandler> handlerBinder = Multibinder.newSetBinder(binder(), CommandHandler.class);
    for(Class<? extends CommandHandler> handler : HANDLERS)
      handlerBinder.addBinding().to(handler);
  }

}
