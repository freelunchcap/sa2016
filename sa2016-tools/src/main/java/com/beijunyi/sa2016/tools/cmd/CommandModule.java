package com.beijunyi.sa2016.tools.cmd;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class CommandModule extends AbstractModule {

  private static final Collection<Class<? extends Command>> COMMANDS
    = ImmutableSet.of(ExtractCommand.class);

  @Override
  protected void configure() {
    Multibinder<Command> binder = Multibinder.newSetBinder(binder(), Command.class);
    COMMANDS.forEach(binder.addBinding()::to);
  }

}
