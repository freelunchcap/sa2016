package com.beijunyi.sa2016.assets;

import java.util.Map;
import javax.annotation.Nonnull;

public class Character implements Asset {

  private final String id;
  private final Map<Action, Map<Direction, String>> animations;

  public Character(String id, Map<Action, Map<Direction, String>> animations) {
    this.id = id;
    this.animations = animations;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  @Nonnull
  public Map<Action, Map<Direction, String>> getAnimations() {
    return animations;
  }

}
