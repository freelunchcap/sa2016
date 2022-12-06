package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.LegacyAnimationFrame;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class LegacyAnimationFrameSerializer extends Serializer<LegacyAnimationFrame> {

  @Override
  public void write(Kryo kryo, Output output, LegacyAnimationFrame object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public LegacyAnimationFrame read(Kryo kryo, Input input, Class<? extends LegacyAnimationFrame> type) {
    int image = (int) LE.uint32(input);
    input.skip(4); // unknown
    int impact = LE.uint8(input);
    int dodge = LE.uint8(input);
    return new LegacyAnimationFrame(image, impact, dodge);
  }

}
