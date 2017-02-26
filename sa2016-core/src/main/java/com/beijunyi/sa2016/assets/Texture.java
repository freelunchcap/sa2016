package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Texture implements Asset {

  private final int id;
  private final String format;
  private final byte[] data;

  public Texture(int id, String format, byte[] data) {
    this.id = id;
    this.format = format;
    this.data = data;
  }

  @Override
  public int getId() {
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
