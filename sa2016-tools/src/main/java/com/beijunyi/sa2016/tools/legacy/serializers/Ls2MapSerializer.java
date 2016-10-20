package com.beijunyi.sa2016.tools.legacy.serializers;

import java.nio.charset.Charset;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Ls2Map;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.BE;

public class Ls2MapSerializer extends Serializer<Ls2Map> {

  private static final Charset GBK = Charset.forName("GBK");

  @Override
  public void write(Kryo kryo, Output output, Ls2Map object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Ls2Map read(Kryo kryo, Input input, Class<Ls2Map> type) {
    input.skip(6);
    int id = BE.uint16(input);
    String name = readName(input);
    int east = BE.uint16(input);
    int south = BE.uint16(input);
    int total = east * south;
    int[] tiles = readIntegerArray(total, input);
    int[] objects = readIntegerArray(total, input);
    return new Ls2Map(id, name, east, south, tiles, objects);
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
