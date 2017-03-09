package com.beijunyi.sa2016.tools.converters.sprite;

import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.REAL;

@Singleton
public class SpriteAssetFactory {

  private final Path archive;

  @Inject
  SpriteAssetFactory(ResourcesProvider resources) {
    archive = resources.getClientResource(REAL);
  }

  @Nonnull
  SpriteAsset createAsset(Adrn header) {
    int id = header.getUid();
    SpriteDataProvider data = new SpriteDataProvider(archive, header.getAddress());
    return new SpriteAsset(id, header, data);
  }

}
