package com.beijunyi.sa2016.extraction.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;

public interface Command {

  @Nonnull
  String getName();

  void call() throws IOException;

}
