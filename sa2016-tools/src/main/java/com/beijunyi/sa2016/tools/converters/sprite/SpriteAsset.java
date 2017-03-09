package com.beijunyi.sa2016.tools.converters.sprite;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;

class SpriteAsset {

  private final int id;
  private final Adrn header;
  private final SpriteDataProvider data;

  SpriteAsset(int id, Adrn header, SpriteDataProvider data) {
    this.id = id;
    this.header = header;
    this.data = data;
  }

  int getId() {
    return id;
  }

  @Nonnull
  Adrn getHeader() {
    return header;
  }

  @Nonnull
  Real readData() throws IOException {
    return data.read();
  }

}
