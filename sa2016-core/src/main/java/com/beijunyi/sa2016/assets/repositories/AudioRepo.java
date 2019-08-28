package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Audio;
import javax.annotation.Nonnull;
import javax.inject.Singleton;

@Singleton
public class AudioRepo extends AssetRepo<Audio> {

  @Nonnull
  @Override
  protected String namespace() {
    return "audios";
  }

  @Nonnull
  @Override
  protected Class<Audio> type() {
    return Audio.class;
  }


}
