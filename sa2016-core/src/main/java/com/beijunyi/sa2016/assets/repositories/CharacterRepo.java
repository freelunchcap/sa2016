package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.serializers.CharacterSerializer;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CharacterRepo extends AssetRepo<Character> {

  @Inject
  public CharacterRepo(CacheProvider cache) {
    super(cache, new CharacterSerializer());
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
