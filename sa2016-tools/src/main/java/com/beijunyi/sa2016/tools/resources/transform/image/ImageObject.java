package com.beijunyi.sa2016.tools.resources.transform.image;

import javax.annotation.Nonnull;

public class ImageObject {

  private final int x;
  private final int y;
  private final Bitmap bitmap;

  public ImageObject(int x, int y, @Nonnull Bitmap bitmap) {
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
