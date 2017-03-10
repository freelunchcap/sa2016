package com.beijunyi.sa2016.tools.converters.character;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.ActType;
import com.beijunyi.sa2016.assets.Direction;
import com.beijunyi.sa2016.tools.legacy.Spr;
import com.beijunyi.sa2016.tools.legacy.SprAdrn;
import com.google.common.collect.ImmutableTable;

class CharacterAsset {

  private final int id;
  private final SprAdrn index;
  private final ImmutableTable<ActType, Direction, Spr> data;

  CharacterAsset(SprAdrn index, ImmutableTable<ActType, Direction, Spr> data) {
    this.id = index.getId();
    this.index = index;
    this.data = data;
  }

  int getId() {
    return id;
  }

  @Nonnull
  SprAdrn getIndex() {
    return index;
  }

  @Nonnull
  ImmutableTable<ActType, Direction, Spr> getData() {
    return data;
  }

}
