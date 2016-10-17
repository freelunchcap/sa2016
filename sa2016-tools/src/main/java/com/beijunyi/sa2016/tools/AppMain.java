package com.beijunyi.sa2016.tools;

import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.api.ApiModule;
import com.beijunyi.sa2016.tools.cmd.CommandModule;
import com.beijunyi.sa2016.tools.cmd.CommandService;
import com.beijunyi.sa2016.tools.demo.DemoModule;
import com.beijunyi.sa2016.tools.resources.ResourcesModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import static java.util.Arrays.asList;

public class AppMain {

  private static final List<? extends Module> MODULES
    = asList
        (
          new ApiModule(),
          new CommandModule(),
          new DemoModule(),
          new ResourcesModule()
        );

  public static void main(@Nonnull String[] args) throws IOException {
    Injector guice = Guice.createInjector(MODULES);
    guice.getInstance(CommandService.class).process(args);
  }

}
