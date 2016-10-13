package com.beijunyi.sa2016.tools.model;

import javax.annotation.Nonnull;

public class Frame {

  private final int x;
  private final int y;
  private final Bitmap bitmap;

  public Frame(int x, int y, @Nonnull Bitmap bitmap) {
    this.x = x;
    this.y = y;
    this.bitmap = bitmap;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Nonnull
  public Bitmap getBitmap() {
    return bitmap;
  }
}
