package com.beijunyi.sa2016.extraction.resources.legacy;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResourceBase.CLIENT;
import static java.util.regex.Pattern.compile;

public enum LegacyResource {
  ADRN(CLIENT, "data", false, compile("^adrn_(\\d+)\\.bin$"), null),
  REAL(CLIENT, "data", false, compile("^real_(\\d+)\\.bin$"), null),
  SPR_ADRN(CLIENT, "data", false, compile("^spradrn_(\\d+)\\.bin$"), null),
  SPR(CLIENT, "data", false, compile("^spr_(\\d+)\\.bin$"), null),
  ;

  private final LegacyResourceBase base;
  private final String relativePath;
  private final boolean recursive;
  private final Pattern pattern;
  private final byte[] header;

  LegacyResource(@Nonnull LegacyResourceBase base, @Nonnull String relativePath, boolean recursive, @Nullable Pattern pattern, @Nullable byte[] header) {
    this.base = base;
    this.relativePath = relativePath;
    this.recursive = recursive;
    this.pattern = pattern;
    this.header = header;
  }

  @Nonnull
  public LegacyResourceBase getBase() {
    return base;
  }

  @Nonnull
  public String getRelativePath() {
    return relativePath;
  }

  public boolean isRecursive() {
    return recursive;
  }

  @Nullable
  public Pattern getPattern() {
    return pattern;
  }

  @Nullable
  public byte[] getHeader() {
    return header;
  }

}
