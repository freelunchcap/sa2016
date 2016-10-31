package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableTable;

public class Character implements Asset {

  private final String id;
  private final ImmutableTable<Action, Direction, String> animations;

  public Character(String id, ImmutableTable<Action, Direction, String> animations) {
    this.id = id;
    this.animations = animations;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  @Nonnull
  public ImmutableTable<Action, Direction, String> getAnimations() {
    return animations;
  }

}
