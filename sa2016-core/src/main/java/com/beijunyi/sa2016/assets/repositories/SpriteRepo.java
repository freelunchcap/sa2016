package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import org.mapdb.*;

@Singleton
public class SpriteRepo extends AssetRepo<Sprite> {

  @Inject
  public SpriteRepo(DB cache) {
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


}
