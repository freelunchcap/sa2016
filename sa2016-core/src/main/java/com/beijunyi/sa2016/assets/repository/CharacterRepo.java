package com.beijunyi.sa2016.assets.repository;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Character;
import com.esotericsoftware.kryo.Kryo;
import org.mapdb.DB;

@Singleton
public class CharacterRepo extends AssetRepo<Character> {

  @Inject
  public CharacterRepo(DB cache, Kryo kryo) {
    super(cache, kryo);
  }

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
