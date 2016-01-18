package com.beijunyi.sa2016.tools.cmd;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.LegacyImageManager;

public class ExtractCommand extends Command {

  private final LegacyImageManager images;

  @Inject
  public ExtractCommand(@Nonnull EnvironmentContext context, @Nonnull LegacyImageManager images) {
    super(context);
    this.images = images;
  }

  @Nonnull
  @Override
  public String getName() {
    return "extract";
  }

  @Override
  public void call() {

  }
}
