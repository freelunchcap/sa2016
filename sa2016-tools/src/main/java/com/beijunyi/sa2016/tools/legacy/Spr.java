package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

public class Spr {

  private final int direction;
  private final int action;
  private final int duration;
  private final int length;
  private final ImmutableList<SprFrame> frames;

  public Spr(int direction, int action, int duration, int length, ImmutableList<SprFrame> frames) {
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
  public ImmutableList<SprFrame> getFrames() {
    return frames;
  }
  
}
