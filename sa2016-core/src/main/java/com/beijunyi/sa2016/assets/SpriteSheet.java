package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class SpriteSheet {

  private final int frameWidth;
  private final int frameHeight;
  private final int frames;
  private final int xOffset;
  private final int yOffset;
  private final Media media;

  public SpriteSheet(int frameWidth, int frameHeight, int frames, int xOffset, int yOffset, Media media) {
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.frames = frames;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.media = media;
  }

  public int getFrameWidth() {
    return frameWidth;
  }

  public int getFrameHeight() {
    return frameHeight;
  }

  public int getFrames() {
    return frames;
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
