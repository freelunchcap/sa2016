package com.beijunyi.sa2016.tools.resources.transform.image;

import javax.annotation.Nonnull;

public class Bitmap {

  private final int width;
  private final int heigh;
  private final byte[] pixels;

  public Bitmap(int width, int heigh, @Nonnull byte[] pixels) {
    this.width = width;
    this.heigh = heigh;
    this.pixels = pixels;
  }

  public int getWidth() {
    return width;
  }

  public int getHeigh() {
    return heigh;
  }

  @Nonnull
  public byte[] getPixels() {
    return pixels;
  }

}
