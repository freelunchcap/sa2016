package com.beijunyi.sa2016.tools.legacy.providers;

import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.esotericsoftware.kryo.io.Input;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import static com.beijunyi.sa2016.tools.legacy.LegacyResourceType.REAL;

@Singleton
class LegacySpriteFactory implements LegacyAssetFactory<LegacySprite> {

  private final LegacyResourcesProvider resources;

  @Inject
  LegacySpriteFactory(LegacyResourcesProvider resources) {
    this.resources = resources;
  }

  @Nonnull
  @Override
  public LegacySprite createAsset(Input input) {
    LegacySpriteHeader header = KRYO.readObject(input, LegacySpriteHeader.class);
    int id = header.uid();
    long address = header.address();
    LegacySpriteDataSource data =
        new LegacySpriteDataSource(resources.getResourceFile(REAL), address);
    return new LegacySprite(id, header, data);
  }
}
