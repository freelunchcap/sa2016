package com.beijunyi.sa2016.tools.converters.sprite;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.AssetExtractionTaskFactory;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySprite;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteProvider;

@Singleton
class SpriteExtractionTaskFactory extends AssetExtractionTaskFactory<LegacySprite, Sprite> {

  @Inject
  public SpriteExtractionTaskFactory(LegacySpriteProvider provider, SpriteFactory factory, SpriteRepo repo) {
    super(provider, factory, repo);
  }

}
