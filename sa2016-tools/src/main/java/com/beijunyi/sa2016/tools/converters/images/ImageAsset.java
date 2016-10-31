package com.beijunyi.sa2016.tools.converters.images;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.tools.utils.Base62;

class ImageAsset {

  private final String id;
  private final Adrn index;
  private final Real data;

  ImageAsset(Adrn index, Real data) {
    this.id = Base62.encode(index.getUid());
    this.index = index;
    this.data = data;
  }

  @Nonnull
  String getId() {
    return id;
  }

  @Nonnull
  Adrn getIndex() {
    return index;
  }

  @Nonnull
  Real getData() {
    return data;
  }

}
