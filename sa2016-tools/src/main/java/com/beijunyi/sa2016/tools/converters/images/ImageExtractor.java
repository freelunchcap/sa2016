package com.beijunyi.sa2016.tools.converters.images;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ImageExtractor implements AssetExtractor {

  private static final ExecutorService THREADS = ThreadPoolFactory.getInstance();
  private static final Logger LOG = LoggerFactory.getLogger(ImageExtractor.class);

  private final ImageLocator locator;
  private final ImageExtractionTaskFactory tasks;

  @Inject
  public ImageExtractor(ImageLocator locator, ImageExtractionTaskFactory tasks) {
    this.locator = locator;
    this.tasks = tasks;
  }

  @Nonnull
  @Override
  public String name() {
    return "images";
  }

  @Override
  public void extract() {
    locator.imageAssets()
      .forEach((entry) -> {
        if(entry.getWidth() <= 0 || entry.getHeight() <= 0) {
          LOG.warn("Skip image {}: width {}, height {}", entry.getUid(), entry.getWidth(), entry.getHeight());
        } else {
          THREADS.submit(tasks.newExtraction(entry));
        }
      });
  }




}
