package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;

public class LegacySprite extends LegacyAsset {

  private final LegacySpriteHeader header;
  private final LegacySpriteDataSource data;

  LegacySprite(int id, LegacySpriteHeader header, LegacySpriteDataSource data) {
    super(id);
    this.header = header;
    this.data = data;
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
