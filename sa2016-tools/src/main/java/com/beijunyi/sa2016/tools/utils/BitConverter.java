package com.beijunyi.sa2016.tools.utils;

import javax.annotation.Nonnull;

public class BitConverter {

  public static long uint32be(@Nonnull byte[] bs) {
    return (long) (bs[0] & 0xFF) << 24 | (bs[1] & 0xFF) << 16 | (bs[2] & 0xFF) << 8 | bs[3] & 0xFF;
  }

  public static long uint32le(@Nonnull byte[] bs) {
    return (long) (bs[3] & 0xFF) << 24 | (bs[2] & 0xFF) << 16 | (bs[1] & 0xFF) << 8 | bs[0] & 0xFF;
  }

  public static int uint16be(@Nonnull byte[] bs) {
    return (bs[0] & 0xFF) << 8 | bs[1] & 0xFF;
  }

  public static int uint16le(@Nonnull byte[] bs) {
    return (bs[1] & 0xFF) << 8 | bs[0] & 0xFF;
  }

  public static short uint8(byte b) {
    return (short) (b & 0xFF);
  }

  public static int int32be(@Nonnull byte[] bs) {
    return (bs[0] & 0xFF) << 24 | (bs[1] & 0xFF) << 16 | (bs[2] & 0xFF) << 8 | bs[3] & 0xFF;
  }

  public static int int32le(@Nonnull byte[] bs) {
    return (bs[3] & 0xFF) << 24 | (bs[2] & 0xFF) << 16 | (bs[1] & 0xFF) << 8 | bs[0] & 0xFF;
  }

  public static short int16be(@Nonnull byte[] bs) {
    return (short) ((bs[0] & 0xFF) << 8 | bs[1] & 0xFF);
  }

  public static short int16le(@Nonnull byte[] bs) {
    return (short) ((bs[1] & 0xFF) << 8 | bs[0] & 0xFF);
  }

  public static byte int8(byte b) {
    return b;
  }

}
