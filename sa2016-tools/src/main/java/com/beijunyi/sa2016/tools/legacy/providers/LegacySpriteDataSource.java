package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyDataSource;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

class LegacySpriteDataSource extends LegacyDataSource<LegacySpriteData> {

  LegacySpriteDataSource(Path file, long position) {
    super(file, position);
  }

  @Nonnull
  @Override
  protected LegacySpriteData read(Kryo kryo, Input input) {
    return kryo.readObject(input, LegacySpriteData.class);
  }

}
