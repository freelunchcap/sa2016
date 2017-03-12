package com.beijunyi.sa2016.tools.converters.character;

import java.io.IOException;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.tools.converters.AssetFactory;
import com.beijunyi.sa2016.tools.converters.character.utils.SpriteSheetFactory;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.beijunyi.sa2016.tools.legacy.LegacyCharacterData;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacter;
import com.google.common.collect.*;

import static com.google.common.collect.Iterables.getOnlyElement;

@Singleton
class CharacterFactory implements AssetFactory<LegacyCharacter, Character> {

  private final SpriteSheetFactory sheets;

  @Inject
  public CharacterFactory(SpriteSheetFactory sheets) {
    this.sheets = sheets;
  }

  @Nonnull
  @Override
  public Character create(LegacyCharacter legacy) throws IOException {
    int id = legacy.getId();
    LegacyCharacterData data = legacy.readData();
    Map<ActType, Act> acts = createActs(data);
    return new Character(id, acts);
  }

  @Nonnull
  private Map<ActType, Act> createActs(LegacyCharacterData data) {
    return createActs(data.getAnimations());
  }

  @Nonnull
  private Map<ActType, Act> createActs(Collection<LegacyAnimation> animations) {
    Table<ActType, Direction, LegacyAnimation> table = indexAnimations(animations);
    Set<ActType> types = table.rowKeySet();
    ImmutableMap.Builder<ActType, Act> ret = ImmutableMap.builder();
    if(types.size() == 1) {
      ret.put(ActType.UNIQUE, createAct(table.row(getOnlyElement(types))));
    } else {
      for(ActType type : types) {
        ret.put(type, createAct(table.row(type)));
      }
    }
    return ret.build();
  }

  @Nonnull
  private Act createAct(Map<Direction, LegacyAnimation> animationMap) {
    int frames = calculateLength(animationMap.values());
    int duration = calculateDuration(animationMap.values());
    Map<Direction, SpriteSheet> animations = makeSpriteSheets(animationMap);
    List<ActEffect> effects = makeActEffects(animationMap);
    return new Act(frames, duration, animations, effects);
  }

  @Nonnull
  private static Table<ActType, Direction, LegacyAnimation> indexAnimations(Collection<LegacyAnimation> animations) {
    ImmutableTable.Builder<ActType, Direction, LegacyAnimation> ret = ImmutableTable.builder();
    for(LegacyAnimation animation : animations) {
      ret.put(ActType.values()[animation.getAction()], Direction.values()[animation.getDirection()], animation);
    }
    return ret.build();
  }

  private static int calculateDuration(Collection<LegacyAnimation> animations) {
    Integer duration = null;
    for(LegacyAnimation animation : animations) {
      if(duration == null) {
        duration = animation.getLength();
      } else if(duration != animation.getDuration()) {
        throw new IllegalStateException("Different durations found for same act");
      }
    }
    if(duration == null) {
      throw new IllegalStateException("Act duration must not be 0");
    }
    return duration;
  }

  private static int calculateLength(Collection<LegacyAnimation> animations) {
    Integer length = null;
    for(LegacyAnimation animation : animations) {
      if(length == null) {
        length = animation.getLength();
      } else if(length != animation.getLength()) {
        throw new IllegalStateException("Different lengths found for same act");
      }
    }
    if(length == null) {
      throw new IllegalStateException("Act length must not be 0");
    }
    return length;
  }

  @Nonnull
  private Map<Direction, SpriteSheet> makeSpriteSheets(Map<Direction, LegacyAnimation> animations) {
    ImmutableMap.Builder<Direction, SpriteSheet> ret = ImmutableMap.builder();
    if(animations.size() == 1) {
      ret.put(Direction.UNIQUE, sheets.create(getOnlyElement(animations.values())));
    } else {
      for(Map.Entry<Direction, LegacyAnimation> animation : animations.entrySet()) {
        ret.put(animation.getKey(), sheets.create(animation.getValue()));
      }
    }
    return ret.build();
  }


  @Nonnull
  private List<ActEffect> makeActEffects(Map<Direction, LegacyAnimation> animations) {
    ImmutableList.Builder<ActEffect> ret = ImmutableList.builder();
    return ret.build();
  }

}
