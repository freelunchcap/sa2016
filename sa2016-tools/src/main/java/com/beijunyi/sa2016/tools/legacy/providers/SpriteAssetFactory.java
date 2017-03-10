package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.sprite.SpriteAsset;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.REAL;

@Singleton
class SpriteAssetFactory {

  private final Path archive;

  @Inject
  SpriteAssetFactory(ResourcesProvider resources) {
    archive = resources.getClientResource(REAL);
  }

  @Nonnull
  SpriteAsset createAsset(LegacySpriteHeader header) {
    int id = header.getUid();
    SpriteDataProvider data = new SpriteDataProvider(archive, header.getAddress());
    return new SpriteAsset(id, header, data);
  }

}
