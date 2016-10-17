package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Image implements Asset {

  private final String id;
  private final String format;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final byte[] data;

  public Image(String id, String format, int x, int y, int width, int height, byte[] data) {
    this.id = id;
    this.format = format;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
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

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public byte[] getData() {
    return data;
  }

}
