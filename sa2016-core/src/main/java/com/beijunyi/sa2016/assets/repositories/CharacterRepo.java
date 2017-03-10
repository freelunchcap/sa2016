package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Character;
import org.mapdb.DB;

@Singleton
public class CharacterRepo extends AssetRepo<Character> {

  @Inject
  public CharacterRepo(DB cache) {
    super(cache);
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "character";
  }

  @Nonnull
  @Override
  protected Class<Character> type() {
    return Character.class;
  }


}
