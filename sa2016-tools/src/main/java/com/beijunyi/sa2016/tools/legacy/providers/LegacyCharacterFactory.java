package com.beijunyi.sa2016.tools.legacy.providers;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.legacy.LegacyResourceType.SPR;

@Singleton
class LegacyCharacterFactory implements LegacyAssetFactory<LegacyCharacter> {

  private final LegacyResourcesProvider resources;

  @Inject
  LegacyCharacterFactory(LegacyResourcesProvider resources) {
    this.resources = resources;
  }

  @Nonnull
  @Override
  public LegacyCharacter createAsset(Input input) {
    LegacyCharacterHeader header = KRYO.readObject(input, LegacyCharacterHeader.class);
    int id = header.getId();
    long address = header.getAddress();
    int animationNum = header.getAnimations();
    LegacyCharacterDataSource data = new LegacyCharacterDataSource(resources.getResourceFile(SPR), address, animationNum);
    return new LegacyCharacter(id, header, data);
  }

}
