package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class LegacySpriteHeaderSerializer extends Serializer<LegacySpriteHeader> {

  @Override
  public void write(Kryo kryo, Output output, LegacySpriteHeader object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public LegacySpriteHeader read(Kryo kryo, Input input, Class<LegacySpriteHeader> type) {
    return new LegacySpriteHeader(
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
