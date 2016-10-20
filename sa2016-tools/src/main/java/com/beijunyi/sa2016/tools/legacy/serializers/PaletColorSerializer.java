package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.PaletColor;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class PaletColorSerializer extends Serializer<PaletColor> {

  @Override
  public void write(Kryo kryo, Output output, PaletColor object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PaletColor read(Kryo kryo, Input input, Class<PaletColor> type) {
    int blue = LE.uint8(input);
    int green = LE.uint8(input);
    int red =  LE.uint8(input);
    return new PaletColor(red, green, blue);
  }

}
