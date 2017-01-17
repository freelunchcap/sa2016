package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Texture;
import org.mapdb.DB;

@Singleton
public class TextureRepo extends AssetRepo<Texture> {

  @Inject
  public TextureRepo(DB cache) {
    super(cache);
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "texture";
  }

  @Nonnull
  @Override
  protected Class<Texture> type() {
    return Texture.class;
  }


}
