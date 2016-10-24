package com.beijunyi.sa2016.tools;

import java.io.IOException;

import com.beijunyi.sa2016.tools.cmd.CommandModule;
import com.beijunyi.sa2016.tools.cmd.CommandService;
import com.beijunyi.sa2016.tools.converters.ExtractorModule;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AppMain {

  private static final ImmutableCollection<? extends Module> MODULES
    = ImmutableSet.of
        (
          new CommandModule(),
          new ExtractorModule()
        );

  public static void main(String[] args) throws IOException {
    Injector guice = Guice.createInjector(MODULES);
    guice.getInstance(CommandService.class).process(args);
  }

}
