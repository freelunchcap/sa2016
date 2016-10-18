package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableTable;

public class Character implements Asset {

  private final String id;
  private final int actions;
  private final ImmutableTable<Action, Direction, String> animations;

  public Character(String id, int actions, ImmutableTable<Action, Direction, String> animations) {
    this.id = id;
    this.actions = actions;
    this.animations = animations;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  public int getActions() {
    return actions;
  }

  @Nonnull
  public ImmutableTable<Action, Direction, String> getAnimations() {
    return animations;
  }

}
