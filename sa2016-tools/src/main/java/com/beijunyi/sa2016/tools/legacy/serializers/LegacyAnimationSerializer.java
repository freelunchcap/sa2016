package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class LegacyAnimationSerializer extends Serializer<LegacyAnimation> {

  @Override
  public void write(Kryo kryo, Output output, LegacyAnimation object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public LegacyAnimation read(Kryo kryo, Input input, Class<LegacyAnimation> type) {
    int direction = LE.uint16(input);
    int action = LE.uint16(input);
    int duration = (int) LE.uint32(input);
    int length = (int) LE.uint32(input);
    ImmutableList.Builder<LegacyAnimation.Frame> frames = ImmutableList.builder();
    for(int i = 0; i < length; i++) {
      int image = (int) LE.uint32(input);
      input.skip(4); // unknown
      int impact = LE.uint8(input);
      int dodge = LE.uint8(input);
      frames.add(new LegacyAnimation.Frame(image, impact, dodge));
    }
    return new LegacyAnimation(direction, action, duration, length, frames.build());
  }
}
