package com.beijunyi.sa2016.assets.repository;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Audio;
import com.beijunyi.sa2016.assets.Image;
import com.esotericsoftware.kryo.Kryo;
import org.mapdb.DB;

@Singleton
public class AudioRepo extends AssetRepo<Audio> {

  @Inject
  public AudioRepo(DB cache, Kryo kryo) {
    super(cache, kryo);
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
