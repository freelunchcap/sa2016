package com.beijunyi.sa2016.tools.converters.sprite;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.graphics.SpriteFactory;

class ImageExtractionTask implements Callable<Sprite> {

  private final int id;
  private final SpriteAssetsManager assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  ImageExtractionTask(int id, SpriteAssetsManager assets, SpriteFactory factory, SpriteRepo repo) {
    this.id = id;
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public Sprite call() {
    Sprite sprite = repo.get(id);
    if(sprite == null) {
      SpriteAsset asset = assets.get(id);
      sprite = factory.create(asset);
      repo.put(sprite);
    }
    return sprite;
  }

}
