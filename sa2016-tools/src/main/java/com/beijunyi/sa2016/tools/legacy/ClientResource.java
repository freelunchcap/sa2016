package com.beijunyi.sa2016.tools.legacy;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.lang.System.getProperty;

public enum ClientResource implements LegacyResource {
  ADRN("data", getProperty("sa.client.adrn", "adrn_*.bin")),
  REAL("data", getProperty("sa.client.real", "real_*.bin")),
  SPR("data", getProperty("sa.client.spr", "spr_*.bin")),
  SPRADRN("data", getProperty("sa.client.spradrn", "spradrn_*.bin")),
  PALET("data/pal", getProperty("sa.client.palet", "palet_1.sap"));

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
