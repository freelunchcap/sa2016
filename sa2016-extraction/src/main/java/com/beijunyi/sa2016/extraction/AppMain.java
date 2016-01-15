package com.beijunyi.sa2016.extraction;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.extraction.cmd.CommandModule;
import com.beijunyi.sa2016.extraction.cmd.CommandService;
import com.beijunyi.sa2016.extraction.config.ConfigModule;
import com.beijunyi.sa2016.extraction.resources.SerializationModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AppMain {

  public static List<? extends Module> MODULES = Arrays.asList(
    new CommandModule(),
    new ConfigModule(),
    new SerializationModule()
  );

  public static void main(@Nonnull String[] args) throws IOException {
    Injector guice = Guice.createInjector(MODULES);
    guice.getInstance(CommandService.class).process(args);
  }

}
