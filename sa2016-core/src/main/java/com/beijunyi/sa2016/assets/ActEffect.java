package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public class ActEffect {

  private final int frame;
  private final boolean knockback;
  private final SoundEffect sound;

  public ActEffect(int frame, boolean knockback, SoundEffect sound) {
    this.frame = frame;
    this.knockback = knockback;
    this.sound = sound;
  }

  public int getFrame() {
    return frame;
  }

  public boolean isKnockback() {
    return knockback;
  }

  @Nonnull
  public SoundEffect getSound() {
    return sound;
  }

}
