package com.beijunyi.sa2016.tools.converters;

import java.util.Base64;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.google.common.primitives.Ints;

import static java.util.Base64.getEncoder;

class ImageAsset {

  private static final Base64.Encoder ENCODER = getEncoder().withoutPadding();

  private final String id;
  private final Adrn adrn;
  private final Real real;

  ImageAsset(Adrn adrn, Real real) {
    this.id = ENCODER.encodeToString(Ints.toByteArray(adrn.getUid()));
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
