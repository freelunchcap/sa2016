package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beust.jcommander.ParametersDelegate;

public abstract class Command {

  @ParametersDelegate
  private final EnvironmentContext context;

  public Command(@Nonnull EnvironmentContext context) {
    this.context = context;
  }

  @Nonnull
  public abstract String getName();

  public abstract void call() throws IOException;

}
