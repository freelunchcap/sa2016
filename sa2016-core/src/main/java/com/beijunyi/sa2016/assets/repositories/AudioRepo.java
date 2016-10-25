package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Audio;
import org.mapdb.DB;

@Singleton
public class AudioRepo extends AssetRepo<Audio> {

  @Inject
  public AudioRepo(DB cache) {
    super(cache);
  }

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
