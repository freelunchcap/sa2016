package com.beijunyi.sa2016.tools.legacy;

import java.util.List;
import javax.annotation.Nonnull;

public class LegacyAnimation {

  private final int direction;
  private final int action;
  private final int duration;
  private final int length;
  private final List<LegacyAnimationFrame> frames;

  public LegacyAnimation(int direction, int action, int duration, int length, List<LegacyAnimationFrame> frames) {
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
  public List<LegacyAnimationFrame> getFrames() {
    return frames;
  }

}
