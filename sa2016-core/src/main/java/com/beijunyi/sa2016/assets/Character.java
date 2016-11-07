package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

import com.google.common.collect.Table;

public class Character implements Asset {

  private final String id;
  private final Table<Action, Direction, Animation> animations;

  public Character(String id, Table<Action, Direction, Animation> animations) {
    this.id = id;
    this.animations = animations;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  @Nonnull
  public Table<Action, Direction, Animation> getAnimations() {
    return animations;
  }

}
