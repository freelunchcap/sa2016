package com.beijunyi.sa2016.assets;

import javax.annotation.Nonnull;

public record Media(String format, byte[] data) {

  @Override
  @Nonnull
  public String format() {
    return format;
  }

  @Override
  @Nonnull
  public byte[] data() {
    return data;
  }
}
