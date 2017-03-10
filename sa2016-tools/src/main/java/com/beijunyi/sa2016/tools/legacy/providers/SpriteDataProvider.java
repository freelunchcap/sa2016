package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyDataProvider;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;

class SpriteDataProvider extends LegacyDataProvider<LegacySpriteData> {

  SpriteDataProvider(Path archive, long position) {
    super(archive, position);
  }

  @Nonnull
  @Override
  protected Class<LegacySpriteData> getDataType() {
    return LegacySpriteData.class;
  }

}
