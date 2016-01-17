package com.beijunyi.sa2016.tools.resources.legacy.structs;

import java.awt.*;
import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class PaletColor implements KryoSerializable {

  private int blue;
  private int green;
  private int red;
  private int alpha;

  public int getBlue() {
    return blue;
  }

  public int getGreen() {
    return green;
  }

  public int getRed() {
    return red;
  }

  public int getAlpha() {
    return alpha;
  }

  @Nonnull
  public static PaletColor forRGBA(int r, int g, int b, int a) {
    PaletColor ret = new PaletColor();
    ret.red = r;
    ret.green = g;
    ret.blue = b;
    ret.alpha = a;
    return ret;
  }

  @Nonnull
  public Color toColor() {
    return new Color(red, green, blue, alpha);
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
    alpha = 255;
  }
}
