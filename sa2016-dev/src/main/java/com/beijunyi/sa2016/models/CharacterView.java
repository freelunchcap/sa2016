package com.beijunyi.sa2016.models;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.beijunyi.sa2016.assets.Action;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.Direction;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class CharacterView {

  private final String id;
  private final Map<Action, Map<Direction, String>> animations;

  private CharacterView(String id, Map<Action, Map<Direction, String>> animations) {
    this.id = id;
    this.animations = animations;
  }

  @Nullable
  public static CharacterView view(@Nullable Character character) {
    if(character == null) return null;
    return new CharacterView(character.getId(), character.getAnimations().rowMap());
  }

  @Nonnull
  public static List<CharacterView> view(List<Character> characters) {
    return ImmutableList.copyOf(Lists.transform(characters, CharacterView::view));
  }

  @Nonnull
  public String getId() {
    return id;
  }

  @Nonnull
  public Map<Action, Map<Direction, String>> getAnimations() {
    return animations;
  }
}
