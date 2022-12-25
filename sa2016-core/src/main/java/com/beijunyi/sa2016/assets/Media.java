package com.beijunyi.sa2016.assets;

public record Media(String format, byte[] data) {

  @Override
  public String format() {
    return format;
  }

  @Override
  public byte[] data() {
    return data;
  }
}
