package com.beijunyi.sa2016.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Image;
import com.esotericsoftware.kryo.*;
import org.mapdb.*;

@Singleton
public class ImageRepo extends AssetRepo<Image> {

  @Inject
  public ImageRepo(DB cache, Kryo kryo) {
    super(cache, kryo);
  }

  @Override
  protected String namespace() {
    return "images";
  }

  @Override
  protected Class<Image> type() {
    return Image.class;
  }


}
