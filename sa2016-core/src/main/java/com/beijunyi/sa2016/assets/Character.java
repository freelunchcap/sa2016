package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableTable;

public class Character implements Asset {

  private final String id;
  private final ImmutableTable<Direction, Action, String> animations;

  public Character(String id, ImmutableTable<Direction, Action, String> animations) {
    this.id = id;
    this.animations = animations;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  public ImmutableTable<Direction, Action, String> getAnimations() {
    return animations;
  }

}
