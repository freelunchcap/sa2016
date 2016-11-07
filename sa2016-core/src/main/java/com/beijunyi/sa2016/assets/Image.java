package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Image implements Asset {

  private final String id;
  private final String texture;
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public Image(String id, String texture, int width, int height, int x, int y) {
    this.id = id;
    this.texture = texture;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  @Nonnull
  public String getTexture() {
    return texture;
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

}
