package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterData;
import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;

public class LegacyCharacter extends LegacyAsset {

  private final LegacyCharacterHeader header;
  private final LegacyCharacterDataSource data;

  LegacyCharacter(int id, LegacyCharacterHeader header, LegacyCharacterDataSource data) {
    super(id);
    this.header = header;
    this.data = data;
  }

  @Nonnull
  public LegacyCharacterHeader getHeader() {
    return header;
  }

  @Nonnull
  public LegacyCharacterData readData() throws IOException {
    return data.read();
  }

}
