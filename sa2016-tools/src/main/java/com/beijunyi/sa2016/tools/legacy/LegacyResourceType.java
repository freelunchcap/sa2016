package com.beijunyi.sa2016.tools.legacy;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import static com.beijunyi.sa2016.tools.ToolsContext.*;

public enum LegacyResourceType {

  SERVER(SERVER_RESOURCES),
  CLIENT(CLIENT_RESOURCES);

  private final Path location;

  LegacyResourceType(Path location) {
    this.location = location;
  }

  @Nonnull
  public Path getLocation() {
    return location;
  }

}
