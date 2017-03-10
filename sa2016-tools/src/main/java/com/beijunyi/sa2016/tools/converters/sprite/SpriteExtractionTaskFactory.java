package com.beijunyi.sa2016.tools.converters.sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteAssetProvider;

@Singleton
class SpriteExtractionTaskFactory {

  private final LegacySpriteAssetProvider assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  @Inject
  public SpriteExtractionTaskFactory(LegacySpriteAssetProvider assets, SpriteFactory factory, SpriteRepo repo) {
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  SpriteExtractionTask newExtraction(int id) {
    return new SpriteExtractionTask(id, assets, factory, repo);
  }

}
