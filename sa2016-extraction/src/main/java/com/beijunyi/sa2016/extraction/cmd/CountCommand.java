package com.beijunyi.sa2016.extraction.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.resources.legacy.LegacyImageManager;

public class CountCommand extends AbstractCommand implements Command {

  private final LegacyImageManager images;

  @Inject
  public CountCommand(@Nonnull EnvironmentContext context, @Nonnull LegacyImageManager images) {
    super(context);
    this.images = images;
  }

  @Nonnull
  @Override
  public String getName() {
    return "count";
  }

  @Override
  public void call() throws IOException {
    System.out.println("Images: " + images.count());
  }

}
