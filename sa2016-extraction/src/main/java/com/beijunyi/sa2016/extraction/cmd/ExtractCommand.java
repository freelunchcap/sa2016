package com.beijunyi.sa2016.extraction.cmd;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.resources.legacy.ImageManager;

public class ExtractCommand extends AbstractCommand implements Command {

  private final ImageManager images;

  @Inject
  public ExtractCommand(@Nonnull EnvironmentContext context, @Nonnull ImageManager images) {
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
