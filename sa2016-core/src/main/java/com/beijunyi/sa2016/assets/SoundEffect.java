package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class SoundEffect {

  private final int duration;
  private final Media media;

  public SoundEffect(int duration, Media media) {
    this.duration = duration;
    this.media = media;
  }

  public int getDuration() {
    return duration;
  }

  @Nonnull
  public Media getMedia() {
    return media;
  }

}
