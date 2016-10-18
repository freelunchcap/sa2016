package com.beijunyi.sa2016.assets.repository;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.Image;
import com.esotericsoftware.kryo.Kryo;
import org.mapdb.DB;

@Singleton
public class AnimationRepo extends AssetRepo<Animation> {

  @Inject
  public AnimationRepo(DB cache, Kryo kryo) {
    super(cache, kryo);
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "animations";
  }

  @Nonnull
  @Override
  protected Class<Animation> type() {
    return Animation.class;
  }


}
