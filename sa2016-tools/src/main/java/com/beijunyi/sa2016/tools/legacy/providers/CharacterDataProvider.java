package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterData;
import com.beijunyi.sa2016.tools.legacy.LegacyDataProvider;

class CharacterDataProvider extends LegacyDataProvider<LegacyCharacterData> {

  CharacterDataProvider(Path archive, long position) {
    super(archive, position);
  }

  @Nonnull
  @Override
  protected Class<LegacyCharacterData> getDataType() {
    return LegacyCharacterData.class;
  }

}
