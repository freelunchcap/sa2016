package com.beijunyi.sa2016.assets;

import java.util.Map;
import javax.annotation.Nonnull;

import com.google.common.collect.Table;

public class Character implements Asset {

  private final int id;
  private final Map<ActType, Act> acts;

  public Character(int id, Map<ActType, Act> acts) {
    this.id = id;
    this.acts = acts;
  }

  @Override
  public int getId() {
    return id;
  }

  @Nonnull
  public Map<ActType, Act> getActs() {
    return acts;
  }

}
