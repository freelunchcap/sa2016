package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Path;

@Singleton
public class SpriteRepo extends AssetRepo<Sprite> {

  @Inject
  public SpriteRepo(CacheProvider cache) {
    super(cache);
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "sprites";
  }

  @Nonnull
  @Override
  protected Class<Sprite> type() {
    return Sprite.class;
  }

  @Override
  protected void serialize(Sprite asset, Path dir) {}

  @Override
  protected Sprite deserialize(Path dir) {
    return null;
  }
}
