package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.Palet;
import com.beijunyi.sa2016.tools.legacy.PaletColor;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

public class PaletSerializer extends Serializer<Palet> {

  @Override
  public void write(Kryo kryo, Output output, Palet object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Palet read(Kryo kryo, Input input, Class<Palet> type) {
    ImmutableList.Builder<PaletColor> colors = ImmutableList.builder();
    for(int i = 0; i < 236; i++)
      colors.add(kryo.readObject(input, PaletColor.class));
    return new Palet(colors.build());
  }
}
