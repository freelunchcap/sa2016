package com.beijunyi.sa2016.extraction.resources.legacy;

import javax.annotation.Nonnull;

import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResourceBase.SERVER;
import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResourceBase.CLIENT;

public enum LegacyResourceLocation {
  SERVER_DATA_MAP(SERVER, "data/map", true),
  CLIENT_DATA(CLIENT, "data", false),
  ;

  private final LegacyResourceBase base;
  private final String path;
  private final boolean nested;

  LegacyResourceLocation(@Nonnull LegacyResourceBase base, @Nonnull String path, boolean nested) {
    this.base = base;
    this.path = path;
    this.nested = nested;
  }

  @Nonnull
  public LegacyResourceBase getBase() {
    return base;
  }

  @Nonnull
  public String getPath() {
    return path;
  }

  public boolean isNested() {
    return nested;
  }

}
