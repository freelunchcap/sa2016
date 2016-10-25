package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Animation;
import org.mapdb.DB;

@Singleton
public class AnimationRepo extends AssetRepo<Animation> {

  @Inject
  public AnimationRepo(DB cache) {
    super(cache);
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