package com.beijunyi.sa2016.tools.resources.legacy;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class SprFrame implements KryoSerializable {

  private int image;
  private int unknown;
  private int impactAudio;
  private int dodgeAudio;

  public int getImage() {
    return image;
  }

  public int getUnknown() {
    return unknown;
  }

  public int getImpactAudio() {
    return impactAudio;
  }

  public int getDodgeAudio() {
    return dodgeAudio;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    image = (int) LE.uint32(input);
    unknown = (short) LE.uint32(input);
    impactAudio = LE.uint8(input);
    dodgeAudio = LE.uint8(input);
  }

}
