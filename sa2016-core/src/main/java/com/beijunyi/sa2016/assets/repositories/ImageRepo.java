package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Image;
import org.mapdb.*;

@Singleton
public class ImageRepo extends AssetRepo<Image> {

  @Inject
  public ImageRepo(DB cache) {
    super(cache);
  }

  @Nonnull
  @Override
  protected String namespace() {
    return "images";
  }

  @Nonnull
  @Override
  protected Class<Image> type() {
    return Image.class;
  }


}
