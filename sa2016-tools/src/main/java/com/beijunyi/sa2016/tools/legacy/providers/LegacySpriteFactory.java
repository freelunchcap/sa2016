package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.REAL;

@Singleton
class LegacySpriteFactory implements LegacyAssetFactory<LegacySpriteHeader, LegacySprite> {

  private final Path archive;

  @Inject
  LegacySpriteFactory(ResourcesProvider resources) {
    archive = resources.getClientResource(REAL);
  }

  @Nonnull
  public LegacySprite createAsset(LegacySpriteHeader header) {
    int id = header.getUid();
    LegacySpriteDataSource data = new LegacySpriteDataSource(archive, header.getAddress());
    return new LegacySprite(id, header, data);
  }

}
