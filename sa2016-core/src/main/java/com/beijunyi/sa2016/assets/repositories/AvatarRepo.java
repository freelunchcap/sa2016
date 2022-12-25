package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Avatar;
import com.beijunyi.sa2016.assets.serializers.AvatarSerializer;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AvatarRepo extends AssetRepo<Avatar> {

  @Inject
  public AvatarRepo(CacheProvider cache) {
    super(cache, new AvatarSerializer());
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "avatar";
  }

  @Nonnull
  @Override
  protected Class<Avatar> type() {
    return Avatar.class;
  }
}
