package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.SPR;

@Singleton
class LegacyCharacterFactory implements LegacyAssetFactory<LegacyCharacterHeader, LegacyCharacter> {

  private final Path archive;

  @Inject
  LegacyCharacterFactory(ResourcesProvider resources) {
    archive = resources.getClientResource(SPR);
  }

  @Nonnull
  public LegacyCharacter createAsset(LegacyCharacterHeader header) {
    int id = header.getId();
    int animationNum = header.getAnimations();
    LegacyCharacterDataSource data = new LegacyCharacterDataSource(archive, header.getAddress(), animationNum);
    return new LegacyCharacter(id, header, data);
  }

}
