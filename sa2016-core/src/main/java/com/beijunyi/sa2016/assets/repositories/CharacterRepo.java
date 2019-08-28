package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Character;
import javax.annotation.Nonnull;
import javax.inject.Singleton;

@Singleton
public class CharacterRepo extends AssetRepo<Character> {

  @Nonnull
  @Override
  protected String namespace() {
    return "characters";
  }

  @Nonnull
  @Override
  protected Class<Character> type() {
    return Character.class;
  }


}
