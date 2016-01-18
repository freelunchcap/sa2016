package com.beijunyi.sa2016.tools.demo;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.cmd.EnvironmentContext;

public class DemoConstants {

  public static final int DEMO_PALETTE = 0;
  public static final String DEMO_IMAGE_FORMAT = "png";

  @Nonnull
  public static Path getDemoDir(@Nonnull EnvironmentContext context) {
    Path output = context.getOutput();
    if(output == null)
      throw new IllegalStateException();
    return output.resolve("demo");
  }

}
