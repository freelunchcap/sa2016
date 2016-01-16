package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;

public interface Command {

  @Nonnull
  String getName();

  void call() throws IOException;

}
