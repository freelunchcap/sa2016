package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

public class Real {

  private final String signature;
  private final int major;
  private final int minor;
  private final int width;
  private final int height;
  private final byte[] data;

  public Real(String signature, int major, int minor, int width, int height, byte[] data) {
    this.signature = signature;
    this.major = major;
    this.minor = minor;
    this.width = width;
    this.height = height;
    this.data = data;
  }

  @Nonnull
  public String getSignature() {
    return signature;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Nonnull
  public byte[] getData() {
    return data;
  }

}
