package com.beijunyi.sa2016.tools.converters.sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.tools.converters.AsyncAssetExtractor;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySprite;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteProvider;

@Singleton
public class AsyncSpriteExtractor extends AsyncAssetExtractor<LegacySprite, Sprite> implements AssetExtractor {

  @Inject
  AsyncSpriteExtractor(LegacySpriteProvider provider, SpriteExtractionTaskFactory tasks) {
    super(provider, tasks);
  }

  @Nonnull
  @Override
  public String name() {
    return "sprite";
  }

}
