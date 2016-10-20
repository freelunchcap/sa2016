package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

public class LegacyImageObject {

  private final Adrn adrn;
  private final Real real;

  public LegacyImageObject(Adrn adrn, Real real) {
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
