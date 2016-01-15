package com.beijunyi.sa2016.extraction.resources.legacy;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.extraction.resources.ResourceSignature;

import static com.beijunyi.sa2016.extraction.resources.ResourceSignature.regexPattern;
import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResourceLocation.CLIENT_DATA;

public enum LegacyResource {
  ADRN(CLIENT_DATA, regexPattern("^adrn_(\\d+)\\.bin$")),
  REAL(CLIENT_DATA, regexPattern("^real_(\\d+)\\.bin$")),
  SPR_ADRN(CLIENT_DATA, regexPattern("^spradrn_(\\d+)\\.bin$")),
  SPR(CLIENT_DATA, regexPattern("^spr_(\\d+)\\.bin$")),
  ;

  private final LegacyResourceLocation location;
  private final ResourceSignature signature;

  LegacyResource(@Nonnull LegacyResourceLocation location, @Nonnull ResourceSignature signature) {
    this.location = location;
    this.signature = signature;
  }

  @Nonnull
  public LegacyResourceLocation getLocation() {
    return location;
  }

  @Nonnull
  public ResourceSignature getSignature() {
    return signature;
  }

}
