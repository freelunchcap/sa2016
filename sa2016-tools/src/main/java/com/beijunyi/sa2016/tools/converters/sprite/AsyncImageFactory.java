package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class AsyncImageFactory implements AssetExtractor {

  private static final ExecutorService THREADS = ThreadPoolFactory.getInstance();
  private static final Logger LOG = LoggerFactory.getLogger(AsyncImageFactory.class);

  private final ImageLocator locator;
  private final ImageExtractionTaskFactory tasks;

  @Inject
  public AsyncImageFactory(ImageLocator locator, ImageExtractionTaskFactory tasks) {
    this.locator = locator;
    this.tasks = tasks;
  }

  @Nonnull
  @Override
  public String name() {
    return "sprite";
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
