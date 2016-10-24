package com.beijunyi.sa2016.tools;

import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.cmd.CommandModule;
import com.beijunyi.sa2016.tools.cmd.CommandService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import static java.util.Arrays.asList;

public class AppMain {

  private static final List<? extends Module> MODULES
    = asList
        (
          new CommandModule()
        );

  public static void main(@Nonnull String[] args) throws IOException {
    Injector guice = Guice.createInjector(MODULES);
    guice.getInstance(CommandService.class).process(args);
  }

}
