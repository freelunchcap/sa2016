package com.beijunyi.sa2016.tools.cmd;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class ExtractCommand extends Command {

  @Inject
  public ExtractCommand(@Nonnull EnvironmentContext context) {
    super(context);
  }

  @Nonnull
  @Override
  public String getName() {
    return "extract";
  }

  @Override
  public void call() {

  }
}
