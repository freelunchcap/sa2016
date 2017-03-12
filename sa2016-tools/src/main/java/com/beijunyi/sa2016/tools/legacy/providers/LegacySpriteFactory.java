package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.legacy.LegacyResource.REAL;

@Singleton
class LegacySpriteFactory implements LegacyAssetFactory<LegacySprite> {

  private final Path file;

  @Inject
  LegacySpriteFactory(LegacyResourcesProvider resources) {
    file = resources.getResourceFile(REAL);
  }

  @Nonnull
  @Override
  public LegacySprite createAsset(Input input) {
    LegacySpriteHeader header = KRYO.readObject(input, LegacySpriteHeader.class);
    int id = header.getUid();
    long address = header.getAddress();
    LegacySpriteDataSource data = new LegacySpriteDataSource(file, address);
    return new LegacySprite(id, header, data);
  }

}
