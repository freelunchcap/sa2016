package com.beijunyi.sa2016.extraction.cmd;

import java.nio.file.Path;
import javax.annotation.Nullable;

import com.beust.jcommander.Parameter;

public class EnvironmentContext {

  @Parameter(names = "--server")
  private Path server;

  @Parameter(names = "--client")
  private Path client;

  @Parameter(names = "--output")
  private Path output;

  @Nullable
  public Path getServer() {
    return server;
  }

  @Nullable
  public Path getClient() {
    return client;
  }

  @Nullable
  public Path getOutput() {
    return output;
  }

}
