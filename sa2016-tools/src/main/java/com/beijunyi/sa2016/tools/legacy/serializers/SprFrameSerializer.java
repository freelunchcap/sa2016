package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.SprFrame;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class SprFrameSerializer extends Serializer<SprFrame> {

  @Override
  public void write(Kryo kryo, Output output, SprFrame object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public SprFrame read(Kryo kryo, Input input, Class<SprFrame> type) {
    return new SprFrame((int) LE.uint32(input), (int) LE.uint32(input), LE.uint8(input), LE.uint8(input));
  }
}
