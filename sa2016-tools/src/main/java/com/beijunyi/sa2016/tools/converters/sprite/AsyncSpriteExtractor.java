package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteAssetProvider;

@Singleton
public class AsyncSpriteExtractor implements AssetExtractor {

  private final LegacySpriteAssetProvider assets;
  private final SpriteExtractionTaskFactory tasks;

  @Inject
  public AsyncSpriteExtractor(LegacySpriteAssetProvider assets, SpriteExtractionTaskFactory tasks) {
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
