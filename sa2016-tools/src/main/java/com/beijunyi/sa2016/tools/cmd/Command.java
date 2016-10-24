package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;

public abstract class Command {

  @Nonnull
  public abstract String getName();

  public abstract void call() throws IOException;

}
