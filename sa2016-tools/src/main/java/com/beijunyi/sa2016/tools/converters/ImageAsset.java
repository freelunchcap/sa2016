package com.beijunyi.sa2016.tools.converters;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.google.common.primitives.Ints;

import static java.util.Base64.getEncoder;

public class ImageAsset {

  private final String id;
  private final Adrn adrn;
  private final Real real;

  public ImageAsset(Adrn adrn, Real real) {
    this.id = getEncoder().encodeToString(Ints.toByteArray(adrn.getUid()));
    this.adrn = adrn;
    this.real = real;
  }

  @Nonnull
  public String getId() {
    return id;
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
