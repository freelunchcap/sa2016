package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.Real;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class RealSerializer extends Serializer<Real> {

  @Override
  public void write(Kryo kryo, Output output, Real object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Real read(Kryo kryo, Input input, Class<Real> type) {
    String signature = new String(input.readBytes(2));
    int major = LE.uint8(input);
    int minor = LE.uint8(input);
    int width = (int) LE.uint32(input);
    int height = (int) LE.uint32(input);
    byte[] data;
    if(major == 1) {
      int length = (int) LE.uint32(input);
      data = new byte[length];
      if(input.read(data) != data.length)
        throw new IllegalStateException();
    } else {
      input.skip(4);
      data = new byte[width * height];
      if(input.read(data) != data.length)
        throw new IllegalStateException();
    }
    return new Real(signature, major, minor, width, height, data);
  }
}
