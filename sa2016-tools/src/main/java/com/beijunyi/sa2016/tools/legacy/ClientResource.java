package com.beijunyi.sa2016.tools.legacy;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum ClientResource implements LegacyResource {
  ADRN("data", "adrn_*.bin"),
  REAL("data", "real_*.bin"),
  SPR("data", "spr_*.bin"),
  SPRADRN("data", "spradrn_*.bin"),
  PALET("data/pal", "palet_0.sap");

  private final String path;
  private final String pattern;

  ClientResource(String path, String pattern) {
    this.path = path;
    this.pattern = pattern;
  }

  @Nonnull
  @Override
  public String path() {
    return path;
  }

  @Nullable
  @Override
  public PathMatcher pattern() {
    return FileSystems.getDefault().getPathMatcher("glob:" + pattern);
  }

  @Nullable
  @Override
  public byte[] header() {
    return null;
  }

  @Override
  public boolean recursive() {
    return false;
  }


}
