package com.beijunyi.sa2016.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.beijunyi.sa2016.CoreModule;
import com.beijunyi.sa2016.tools.cmd.CommandService;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AppMain {

  private static final ImmutableCollection<? extends Module> MODULES
    = ImmutableSet.of
        (
          new CoreModule(),
          new ToolsModule()
        );

  public static void main(String[] args) throws Exception {
    Injector guice = Guice.createInjector(MODULES);
    guice.getInstance(CommandService.class).process(args);
    shutdownThreadPool();
  }

  private static void shutdownThreadPool() throws Exception {
    ExecutorService threads = ThreadPoolFactory.getInstance();
    threads.shutdown();
    threads.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
  }

}
