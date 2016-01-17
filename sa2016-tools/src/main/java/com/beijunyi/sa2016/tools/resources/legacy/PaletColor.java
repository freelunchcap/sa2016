package com.beijunyi.sa2016.tools.resources.legacy;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.utils.IntegerReader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class PaletColor implements KryoSerializable {

  private int blue;
  private int green;
  private int red;

  public int getBlue() {
    return blue;
  }

  public int getGreen() {
    return green;
  }

  public int getRed() {
    return red;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    blue = LE.uint8(input);
    green = LE.uint8(input);
    red =  LE.uint8(input);
  }
}
