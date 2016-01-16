package com.beijunyi.sa2016.tools.cmd;

import javax.annotation.Nonnull;

import com.beust.jcommander.ParametersDelegate;

public abstract class AbstractCommand implements Command {

  @ParametersDelegate
  private final EnvironmentContext context;

  public AbstractCommand(@Nonnull EnvironmentContext context) {
    this.context = context;
  }

}
