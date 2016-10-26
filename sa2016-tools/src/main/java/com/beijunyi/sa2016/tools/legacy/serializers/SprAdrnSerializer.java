package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.SprAdrn;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class SprAdrnSerializer extends Serializer<SprAdrn> {

  @Override
  public void write(Kryo kryo, Output output, SprAdrn object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public SprAdrn read(Kryo kryo, Input input, Class<SprAdrn> type) {
    return new SprAdrn((int) LE.uint32(input), LE.uint32(input), LE.uint16(input), LE.uint16(input));
  }
}
