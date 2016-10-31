package com.beijunyi.sa2016.tools.converters.characters;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Action;
import com.beijunyi.sa2016.assets.Direction;
import com.beijunyi.sa2016.tools.legacy.Spr;
import com.beijunyi.sa2016.tools.legacy.SprAdrn;
import com.beijunyi.sa2016.tools.utils.Base62;
import com.google.common.collect.ImmutableTable;

class CharacterAsset {

  private final String id;
  private final SprAdrn index;
  private final ImmutableTable<Action, Direction, Spr> data;

  CharacterAsset(SprAdrn index, ImmutableTable<Action, Direction, Spr> data) {
    this.id = Base62.encode(index.getId());
    this.index = index;
    this.data = data;
  }

  @Nonnull
  String getId() {
    return id;
  }

  @Nonnull
  SprAdrn getIndex() {
    return index;
  }

  @Nonnull
  ImmutableTable<Action, Direction, Spr> getData() {
    return data;
  }

}
