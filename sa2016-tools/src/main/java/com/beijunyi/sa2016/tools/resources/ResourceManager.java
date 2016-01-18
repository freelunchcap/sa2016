package com.beijunyi.sa2016.tools.resources;

import java.io.IOException;
import javax.annotation.Nonnull;

public interface ResourceManager<RESOURCE> {

  @Nonnull
  ResourceType getResourceType();

  int count() throws IOException;

  @Nonnull
  RESOURCE getResource(int id) throws IOException;

}
