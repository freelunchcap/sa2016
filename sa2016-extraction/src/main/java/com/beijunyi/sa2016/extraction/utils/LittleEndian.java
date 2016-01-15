package com.beijunyi.sa2016.extraction.utils;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.io.Input;

public final class LittleEndian implements IntegerReader {

  public long uint32(@Nonnull Input input) {
    return BitConverter.uint32le(input.readBytes(4));
  }

  public int uint16(@Nonnull Input input) {
    return BitConverter.uint16le(input.readBytes(2));
  }

  public short uint8(@Nonnull Input input) {
    return BitConverter.uint8(input.readByte());
  }

  public int int32(@Nonnull Input input) {
    return BitConverter.int32le(input.readBytes(4));
  }

  public short int16(@Nonnull Input input) {
    return BitConverter.int16le(input.readBytes(2));
  }

  public byte int8(@Nonnull Input input) {
    return BitConverter.int8(input.readByte());
  }

}
