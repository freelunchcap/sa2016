package com.beijunyi.sa2016.tools.legacy;

import java.nio.file.PathMatcher;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum ServerResource implements LegacyResource{
  ;

  private final String path;
  private final PathMatcher pattern;
  private final byte[] header;
  private final boolean recursive;

  ServerResource(String path, PathMatcher pattern, byte[] header, boolean recursive) {
    this.path = path;
    this.pattern = pattern;
    this.header = header;
    this.recursive = recursive;
  }

  @Nonnull
  @Override
  public String path() {
    return path;
  }

  @Nullable
  @Override
  public PathMatcher pattern() {
    return pattern;
  }

  @Nullable
  @Override
  public byte[] header() {
    return header;
  }

  @Override
  public boolean recursive() {
    return recursive;
  }

}
