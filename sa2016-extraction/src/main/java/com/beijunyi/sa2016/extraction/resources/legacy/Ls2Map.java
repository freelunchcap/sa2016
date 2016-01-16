package com.beijunyi.sa2016.extraction.resources.legacy;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.extraction.utils.IntegerReader.BE;

public class Ls2Map implements KryoSerializable {

  private static final Charset GBK = Charset.forName("GBK");

  private int id;
  private String name;
  private int east;
  private int south;
  private int[] tiles;
  private int[] objects;

  public int getId() {
    return id;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  public int getEast() {
    return east;
  }

  public int getSouth() {
    return south;
  }

  public int[] getTiles() {
    return tiles;
  }

  public int[] getObjects() {
    return objects;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    input.skip(6);
    id = BE.uint16(input);
    name = readName(input);
    east = BE.uint16(input);
    south = BE.uint16(input);
    int total = east * south;
    tiles = readIntegerArray(total, input);
    objects = readIntegerArray(total, input);
  }

  @Nonnull
  private static String readName(@Nonnull Input input) {
    byte[] bytes = input.readBytes(32);
    int length = 0;
    while(length < bytes.length) {
      byte b = bytes[length];
      if(b == '\0' || b =='|')
        break;
      length++;
    }
    return new String(bytes, 0, length, GBK);
  }

  @Nonnull
  private static int[] readIntegerArray(int total, @Nonnull Input input) {
    int[] ret = new int[total];
    for(int i = 0; i < total; i++)
      ret[i] = BE.uint16(input);
    return ret;
  }

}
