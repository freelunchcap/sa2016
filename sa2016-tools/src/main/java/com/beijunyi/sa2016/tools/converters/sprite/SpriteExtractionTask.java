package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;

class SpriteExtractionTask implements Supplier<Sprite> {

  private final int id;
  private final SpriteAssetsManager assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  SpriteExtractionTask(int id, SpriteAssetsManager assets, SpriteFactory factory, SpriteRepo repo) {
    this.id = id;
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public Sprite get() {
    Sprite sprite = repo.get(id);
    if(sprite == null) {
      SpriteAsset asset = assets.get(id);
      sprite = factory.create(asset);
      repo.put(sprite);
    }
    return sprite;
  }

}
