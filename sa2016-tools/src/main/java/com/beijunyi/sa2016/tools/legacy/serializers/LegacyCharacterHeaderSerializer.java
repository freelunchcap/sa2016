package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class LegacyCharacterHeaderSerializer extends Serializer<LegacyCharacterHeader> {

  @Override
  public void write(Kryo kryo, Output output, LegacyCharacterHeader object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public LegacyCharacterHeader read(Kryo kryo, Input input, Class<? extends LegacyCharacterHeader> type) {
    return new LegacyCharacterHeader((int) LE.uint32(input), LE.uint32(input), LE.uint16(input), LE.uint16(input));
  }
}
