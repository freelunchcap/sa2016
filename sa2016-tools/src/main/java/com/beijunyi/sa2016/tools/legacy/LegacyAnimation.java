package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

public class LegacyAnimation {

  private final int direction;
  private final int action;
  private final int duration;
  private final int length;
  private final ImmutableList<Frame> frames;

  public LegacyAnimation(int direction, int action, int duration, int length, ImmutableList<Frame> frames) {
    this.direction = direction;
    this.action = action;
    this.duration = duration;
    this.length = length;
    this.frames = frames;
  }

  public int getDirection() {
    return direction;
  }

  public int getAction() {
    return action;
  }

  public int getDuration() {
    return duration;
  }

  public int getLength() {
    return length;
  }

  @Nonnull
  public ImmutableList<Frame> getFrames() {
    return frames;
  }

  public static class Frame {

    private final int image;
    private final int impactAudio;
    private final int dodgeAudio;

    public Frame(int image, int impactAudio, int dodgeAudio) {
      this.image = image;
      this.impactAudio = impactAudio;
      this.dodgeAudio = dodgeAudio;
    }

    public int getImage() {
      return image;
    }

    public int getImpactAudio() {
      return impactAudio;
    }

    public int getDodgeAudio() {
      return dodgeAudio;
    }

  }

}
