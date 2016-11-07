package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class AudioTrigger {

  private final int frame;
  private final String audio;

  public AudioTrigger(int frame, String audio) {
    this.frame = frame;
    this.audio = audio;
  }

  public int getFrame() {
    return frame;
  }

  @Nonnull
  public String getAudio() {
    return audio;
  }

}
