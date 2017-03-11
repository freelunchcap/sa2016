package com.beijunyi.sa2016.tools.converters.sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteProvider;

@Singleton
class SpriteExtractionTaskFactory {

  private final LegacySpriteProvider assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  @Inject
  public SpriteExtractionTaskFactory(LegacySpriteProvider assets, SpriteFactory factory, SpriteRepo repo) {
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  SpriteExtractionTask newExtraction(int id) {
    return new SpriteExtractionTask(id, assets, factory, repo);
  }

}
