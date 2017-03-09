package com.beijunyi.sa2016.tools.converters.sprite;

import java.nio.file.Path;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.converters.ArchiveDataProvider;
import com.beijunyi.sa2016.tools.legacy.Real;

class SpriteDataProvider extends ArchiveDataProvider<Real> {

  SpriteDataProvider(Path archive, long position) {
    super(archive, position);
  }

  @Nonnull
  @Override
  protected Class<Real> getDataType() {
    return Real.class;
  }

}
