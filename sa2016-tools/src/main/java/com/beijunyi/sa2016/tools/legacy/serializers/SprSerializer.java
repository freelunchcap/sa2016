package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Spr;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class SprSerializer extends Serializer<Spr> {

  @Override
  public void write(Kryo kryo, Output output, Spr object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public Spr read(Kryo kryo, Input input, Class<Spr> type) {
    int direction = LE.uint16(input);
    int action = LE.uint16(input);
    int duration = (int) LE.uint32(input);
    int length = (int) LE.uint32(input);
    ImmutableList.Builder<Spr.Frame> frames = ImmutableList.builder();
    for(int i = 0; i < length; i++) {
      int image = (int) LE.uint32(input);
      int unknown = (int) LE.uint32(input);
      int impact = LE.uint8(input);
      int dodge = LE.uint8(input);
      frames.add(new Spr.Frame(image, unknown, impact, dodge));
    }
    return new Spr(direction, action, duration, length, frames.build());
  }
}
