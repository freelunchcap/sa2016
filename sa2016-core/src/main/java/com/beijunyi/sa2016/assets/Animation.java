package com.beijunyi.sa2016.assets;

import java.util.List;
import javax.annotation.Nonnull;

public class Animation implements Asset {

  private final String id;
  private final int duration;
  private final List<Frame> frames;

  public Animation(String id, int duration, List<Frame> frames) {
    this.id = id;
    this.duration = duration;
    this.frames = frames;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  public int getDuration() {
    return duration;
  }

  @Nonnull
  public List<Frame> getFrames() {
    return frames;
  }


  public static class Frame {
    private final String image;
    private final String audio;

    public Frame(String image, String audio) {
      this.image = image;
      this.audio = audio;
    }

    @Nonnull
    public String getImage() {
      return image;
    }

    @Nonnull
    public String getAudio() {
      return audio;
    }
  }

}
