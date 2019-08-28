package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.serializers.AssetSerializer;
import javax.annotation.Nonnull;
import javax.inject.Singleton;

@Singleton
public class AnimationRepo extends AssetRepo<Animation> {

  @Nonnull
  @Override
  protected String namespace() {
    return "animations";
  }

  @Nonnull
  @Override
  protected AssetSerializer<Animation> serializer() {
    return Ani;
  }
}
