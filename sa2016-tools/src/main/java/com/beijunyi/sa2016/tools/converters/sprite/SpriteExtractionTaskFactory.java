package com.beijunyi.sa2016.tools.converters.sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.SpriteRepo;

@Singleton
class SpriteExtractionTaskFactory {

  private final SpriteAssetsManager assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  @Inject
  public SpriteExtractionTaskFactory(SpriteAssetsManager assets, SpriteFactory factory, SpriteRepo repo) {
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  SpriteExtractionTask newExtraction(int id) {
    return new SpriteExtractionTask(id, assets, factory, repo);
  }

}
