package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class ExportCommand extends Command {

  @Inject
  public ExportCommand(@Nonnull EnvironmentContext context) {
    super(context);
  }

  @Nonnull
  @Override
  public String getName() {
    return "export";
  }

  @Override
  public void call() throws IOException {

  }

}
