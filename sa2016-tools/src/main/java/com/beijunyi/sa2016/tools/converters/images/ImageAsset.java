package com.beijunyi.sa2016.tools.converters.images;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;

public class ImageAsset {

  private final int id;
  private final Adrn index;
  private final Real data;

  ImageAsset(Adrn index, Real data) {
    this.id = index.getUid();
    this.index = index;
    this.data = data;
  }

  public int getId() {
    return id;
  }

  @Nonnull
  public Adrn getIndex() {
    return index;
  }

  @Nonnull
  public Real getData() {
    return data;
  }

}
