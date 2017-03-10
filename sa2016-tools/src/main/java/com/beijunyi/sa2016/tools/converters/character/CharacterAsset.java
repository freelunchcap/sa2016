package com.beijunyi.sa2016.tools.converters.character;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.ActType;
import com.beijunyi.sa2016.assets.Direction;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.google.common.collect.ImmutableTable;

class CharacterAsset {

  private final int id;
  private final LegacyCharacterHeader header;
  private final ImmutableTable<ActType, Direction, LegacyAnimation> data;

  CharacterAsset(LegacyCharacterHeader header, ImmutableTable<ActType, Direction, LegacyAnimation> data) {
    this.id = header.getId();
    this.header = header;
    this.data = data;
  }

  int getId() {
    return id;
  }

  @Nonnull
  LegacyCharacterHeader getHeader() {
    return header;
  }

  @Nonnull
  ImmutableTable<ActType, Direction, LegacyAnimation> getData() {
    return data;
  }

}
