package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Image implements Asset {

  private final int id;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final String format;
  private final byte[] bitmap;

  public Image(int id, int width, int height, int x, int y, String format, byte[] bitmap) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    this.format = format;
    this.bitmap = bitmap;
  }

  @Override
  public int getId() {
    return id;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Nonnull
  public String getFormat() {
    return format;
  }

  @Nonnull
  public byte[] getBitmap() {
    return bitmap;
  }
}
