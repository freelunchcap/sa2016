package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class Sprite implements GameAsset {

  private final int id;
  private final int width;
  private final int height;
  private final int xOffset;
  private final int yOffset;
  private final Media media;

  public Sprite(int id, int width, int height, int xOffset, int yOffset, Media media) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.media = media;
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

  public int getXOffset() {
    return xOffset;
  }

  public int getYOffset() {
    return yOffset;
  }

  @Nonnull
  public Media getMedia() {
    return media;
  }

}
