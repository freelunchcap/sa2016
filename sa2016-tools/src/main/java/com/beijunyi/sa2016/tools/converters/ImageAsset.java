package com.beijunyi.sa2016.tools.converters;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;

public class ImageAsset {

  private final Adrn adrn;
  private final Real real;

  public ImageAsset(Adrn adrn, Real real) {
    this.adrn = adrn;
    this.real = real;
  }

  @Nonnull
  public Adrn getAdrn() {
    return adrn;
  }

  @Nonnull
  public Real getReal() {
    return real;
  }

}
