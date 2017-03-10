package com.beijunyi.sa2016.tools.converters.sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.graphics.SpriteFactory;

@Singleton
class ImageExtractionTaskFactory {

  private final SpriteAssetsManager assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  @Inject
  public ImageExtractionTaskFactory(SpriteAssetsManager assets, SpriteFactory factory, SpriteRepo repo) {
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  ImageExtractionTask newExtraction(int id) {
    return new ImageExtractionTask(id, assets, factory, repo);
  }

}
