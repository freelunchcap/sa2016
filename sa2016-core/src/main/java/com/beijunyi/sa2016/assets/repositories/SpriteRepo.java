package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.serializers.SpriteSerializer;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpriteRepo extends AssetRepo<Sprite> {

  @Inject
  public SpriteRepo(CacheProvider cache) {
    super(cache, new SpriteSerializer());
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
}
