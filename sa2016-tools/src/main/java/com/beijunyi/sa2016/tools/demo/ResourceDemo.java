package com.beijunyi.sa2016.tools.demo;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.resources.ResourceType;

public interface ResourceDemo {

  @Nonnull
  ResourceType getResourceType();

  void outputResourceDemo() throws IOException;

}
