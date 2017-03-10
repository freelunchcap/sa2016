package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import com.google.common.util.concurrent.Futures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class AsyncSpriteFactory implements AssetExtractor {

  private static final ExecutorService THREADS = ThreadPoolFactory.getInstance();
  private static final Logger LOG = LoggerFactory.getLogger(AsyncSpriteFactory.class);

  private final SpriteAssetsManager assets;
  private final ImageExtractionTaskFactory tasks;

  @Inject
  public AsyncSpriteFactory(SpriteAssetsManager assets, ImageExtractionTaskFactory tasks) {
    this.assets = assets;
    this.tasks = tasks;
  }

  @Nonnull
  @Override
  public String name() {
    return "sprite";
  }

  @Nonnull
  public Future<Sprite> genSprite(int id) {
    return THREADS.submit(tasks.newExtraction(id));
  }

  @Override
  public void extract() {
    assets.keys()
      .forEach(this::genSprite);
  }

}
