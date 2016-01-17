package com.beijunyi.sa2016.tools.resources.transform.image;

import javax.annotation.Nonnull;

public class ImageObject {

  private final int xOffset;
  private final int yOffset;
  private final Bitmap bitmap;

  public ImageObject(int xOffset, int yOffset, @Nonnull Bitmap bitmap) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.bitmap = bitmap;
  }
}
