package com.beijunyi.sa2016.extraction.utils;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.io.Input;

public interface IntegerReader {

  IntegerReader BE = new BigEndian();

  IntegerReader LE = new LittleEndian();

  long uint32(@Nonnull Input input);

  int uint16(@Nonnull Input input);

  short uint8(@Nonnull Input input);

  int int32(@Nonnull Input input);

  short int16(@Nonnull Input input);

  byte int8(@Nonnull Input input);

}
