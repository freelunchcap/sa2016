package com.beijunyi.sa2016.tools.resources.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Palet implements KryoSerializable {

  private static final int PALETTE_COLORS = 236;

  private final List<PaletColor> colors = new ArrayList<>(PALETTE_COLORS);

  @Nonnull
  public List<PaletColor> getColors() {
    return colors;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    for(int i = 0; i < PALETTE_COLORS; i++)
      colors.add(kryo.readObject(input, PaletColor.class));
  }
}
