package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Character;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Path;

@Singleton
public class CharacterRepo extends AssetRepo<Character> {

  @Inject
  public CharacterRepo(CacheProvider cache) {
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

  @Override
  protected void serialize(Character asset, Path dir) {}

  @Override
  protected Character deserialize(Path dir) {
    return null;
  }
}
