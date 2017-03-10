package com.beijunyi.sa2016.tools.converters.character;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
class CharacterFactory {

  private static final Logger LOG = LoggerFactory.getLogger(CharacterFactory.class);

  private final AnimationFactory factory;
  private final TextureRepo repo;

  @Inject
  public CharacterFactory(AnimationFactory factory, TextureRepo repo) {
    this.factory = factory;
    this.repo = repo;
  }

  Character newCharacter(CharacterAsset asset) {
    ImmutableTable.Builder<ActType, Direction, String> animations = ImmutableTable.builder();
    for(Table.Cell<ActType, Direction, LegacyAnimation> a : asset.getData().cellSet()) {
      ActType actType = a.getRowKey();
      Direction direction = a.getColumnKey();
      LegacyAnimation legacyAnimation = a.getValue();
      if(actType == null || direction == null || legacyAnimation == null)
        throw new IllegalStateException("Incomplete character " + asset.getId() + " " + actType + " " + direction);
      String id = asset.getId() + "-" + actType.ordinal() + "-" + direction.ordinal();
      animations.put(actType, direction, id);
      try {
        repo.put(factory.newAnimation(id, legacyAnimation));
      } catch(Exception e) {
        LOG.warn("Could not add animation {} {} {}", asset.getId(), actType, direction, e);
      }
    }
    return new Character(asset.getId(), animations.build());
  }

}
