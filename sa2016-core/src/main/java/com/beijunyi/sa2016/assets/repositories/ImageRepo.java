package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Image;
import javax.annotation.Nonnull;
import javax.inject.Singleton;

@Singleton
public class ImageRepo extends AssetRepo<Image> {

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
