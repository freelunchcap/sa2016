package com.beijunyi.sa2016.tools.model;

import javax.annotation.Nonnull;

public class Bitmap {

  private final int width;
  private final int height;
  private final byte[] pixels;

  public Bitmap(int width, int height, @Nonnull byte[] pixels) {
    this.width = width;
    this.height = height;
    this.pixels = pixels;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Nonnull
  public byte[] getPixels() {
    return pixels;
  }

}
