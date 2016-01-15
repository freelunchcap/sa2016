package com.beijunyi.sa2016.extraction.context;

import java.nio.file.Path;
import javax.annotation.Nullable;

public class EnvironmentContext {

  private final Path server;
  private final Path client;
  private final Path output;

  public EnvironmentContext(@Nullable Path server, @Nullable Path client, @Nullable Path output) {
    this.server = server;
    this.client = client;
    this.output = output;
  }

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
