package com.beijunyi.sa2016.tools.converters.characters;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.tools.legacy.Spr;
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
    ImmutableTable.Builder<Action, Direction, String> animations = ImmutableTable.builder();
    for(Table.Cell<Action, Direction, Spr> a : asset.getData().cellSet()) {
      Action action = a.getRowKey();
      Direction direction = a.getColumnKey();
      Spr spr = a.getValue();
      if(action == null || direction == null || spr == null)
        throw new IllegalStateException("Incomplete character " + asset.getId() + " " + action + " " + direction);
      String id = asset.getId() + "-" + action.ordinal() + "-" + direction.ordinal();
      animations.put(action, direction, id);
      try {
        repo.put(factory.newAnimation(id, spr));
      } catch(Exception e) {
        LOG.warn("Could not add animation {} {} {}", asset.getId(), action, direction, e);
      }
    }
    return new Character(asset.getId(), animations.build());
  }

}
