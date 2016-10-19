package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class AdrnSerializer extends Serializer<Adrn> {

  @Override
  public void write(Kryo kryo, Output output, Adrn object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public Adrn read(Kryo kryo, Input input, Class<Adrn> type) {
    return new Adrn(
                     LE.int32(input),
                     LE.uint32(input),
                     LE.int32(input),
                     LE.int32(input),
                     LE.int32(input),
                     LE.int32(input),
                     LE.int32(input),
                     LE.uint8(input),
                     LE.uint8(input),
                     LE.uint8(input),
                     new String(input.readBytes(45)),
                     LE.int32(input)
    );
  }

}
