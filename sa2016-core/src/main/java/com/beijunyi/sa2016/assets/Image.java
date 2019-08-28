package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Image implements Asset {

  private final String id;
  private final String format;
  private final byte[] data;

  public Image(String id, String format, byte[] data) {
    this.id = id;
    this.format = format;
    this.data = data;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
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
