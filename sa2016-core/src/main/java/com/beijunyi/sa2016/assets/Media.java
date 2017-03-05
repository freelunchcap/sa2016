package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Media {

  private final String format;
  private final byte[] data;

  public Media(String format, byte[] data) {
    this.format = format;
    this.data = data;
  }

  @Nonnull
  public String getFormat() {
    return format;
  }

  @Nonnull
  public byte[] getData() {
    return data;
  }

}
