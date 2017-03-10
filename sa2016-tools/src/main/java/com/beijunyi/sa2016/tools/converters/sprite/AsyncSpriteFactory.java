package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.converters.AssetExtractor;

@Singleton
public class AsyncSpriteFactory implements AssetExtractor {

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
  public CompletableFuture<Sprite> genSprite(int id) {
    return CompletableFuture.supplyAsync(tasks.newExtraction(id));
  }

  @Override
  public void extract() {
    assets.keys()
      .forEach(this::genSprite);
  }

}
