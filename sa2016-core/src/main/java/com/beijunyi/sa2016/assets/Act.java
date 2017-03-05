package com.beijunyi.sa2016.assets;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

public class Act {

  private final int frames;
  private final int duration;
  private final Map<Direction, SpriteSheet> animations;
  private final List<ActEffect> effects;

  public Act(int frames, int duration, Map<Direction, SpriteSheet> animations, List<ActEffect> effects) {
    this.frames = frames;
    this.duration = duration;
    this.animations = animations;
    this.effects = effects;
  }

  public int getFrames() {
    return frames;
  }

  public int getDuration() {
    return duration;
  }

  @Nonnull
  public Map<Direction, SpriteSheet> getAnimations() {
    return animations;
  }

  @Nonnull
  public List<ActEffect> getEffects() {
    return effects;
  }

}
