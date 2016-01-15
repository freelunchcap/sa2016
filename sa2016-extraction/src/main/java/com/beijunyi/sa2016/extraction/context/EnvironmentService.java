package com.beijunyi.sa2016.extraction.context;

import javax.annotation.Nonnull;

public class EnvironmentService {

  private EnvironmentContext context;

  public void setContext(@Nonnull EnvironmentContext context) {
    this.context = context;
  }

  @Nonnull
  public EnvironmentContext getContext() {
    if(context == null)
      throw new IllegalStateException();
    return context;
  }
}
