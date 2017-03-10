package com.beijunyi.sa2016.tools.converters.sprite;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;
import com.beijunyi.sa2016.tools.legacy.providers.SpriteDataProvider;

public class SpriteAsset {

  private final int id;
  private final LegacySpriteHeader header;
  private final SpriteDataProvider data;

  public SpriteAsset(int id, LegacySpriteHeader header, SpriteDataProvider data) {
    this.id = id;
    this.header = header;
    this.data = data;
  }

  public int getId() {
    return id;
  }

  @Nonnull
  public LegacySpriteHeader getHeader() {
    return header;
  }

  @Nonnull
  public LegacySpriteData readData() throws IOException {
    return data.read();
  }

}
