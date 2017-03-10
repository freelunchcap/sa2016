package com.beijunyi.sa2016.tools.converters.sprite;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;

public class SpriteAsset {

  private final int id;
  private final Adrn header;
  private final SpriteDataProvider data;

  public SpriteAsset(int id, Adrn header, SpriteDataProvider data) {
    this.id = id;
    this.header = header;
    this.data = data;
  }

  public int getId() {
    return id;
  }

  @Nonnull
  public Adrn getHeader() {
    return header;
  }

  @Nonnull
  public Real readData() throws IOException {
    return data.read();
  }

}
