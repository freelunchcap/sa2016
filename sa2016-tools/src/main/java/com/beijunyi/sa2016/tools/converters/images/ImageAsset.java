package com.beijunyi.sa2016.tools.converters.images;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.tools.utils.Base62;

class ImageAsset {

  private final String id;
  private final Adrn adrn;
  private final Real real;

  ImageAsset(Adrn adrn, Real real) {
    this.id = Base62.encode(adrn.getUid());
    this.adrn = adrn;
    this.real = real;
  }

  @Nonnull
  String getId() {
    return id;
  }

  @Nonnull
  Adrn getAdrn() {
    return adrn;
  }

  @Nonnull
  Real getReal() {
    return real;
  }

}
