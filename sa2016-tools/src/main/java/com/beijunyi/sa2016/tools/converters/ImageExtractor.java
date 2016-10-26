package com.beijunyi.sa2016.tools.converters;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
class ImageExtractor implements AssetExtractor {

  private static final ExecutorService THREADS = ThreadPoolFactory.getInstance();
  private static final Logger LOG = LoggerFactory.getLogger(ImageExtractor.class);

  private final ImageLocator images;
  private final ImageExtractionTaskFactory tasks;

  @Inject
  public ImageExtractor(ImageLocator images, ImageExtractionTaskFactory tasks) {
    this.images = images;
    this.tasks = tasks;

  }

  @Nonnull
  @Override
  public String name() {
    return "images";
  }

  @Override
  public void extract() {
    images.imageAssets()
      .forEach((adrn) -> {
        if(adrn.getWidth() <= 0 || adrn.getHeight() <= 0) {
          LOG.warn("Skip image {}: width {}, height {}", adrn.getUid(), adrn.getWidth(), adrn.getHeight());
        } else {
          THREADS.submit(tasks.newTask(adrn));
        }
      });
  }




}
