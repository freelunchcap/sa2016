package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Sprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

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
  protected byte[] serialize(Sprite asset) {
    return new byte[0];
  }

  @Override
  protected Sprite deserialize(byte[] data) {
    return null;
  }
}
