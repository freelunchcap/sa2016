package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.Real;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

class RealSerializer extends Serializer<Real> {

  private static final Logger LOG = LoggerFactory.getLogger(RealSerializer.class);

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
    int expected;
    if(major == 1) {
      expected = (int) LE.uint32(input) - 16; // data length = total size - header size
    } else {
      expected = width * height;
      input.skip(4);
    }
    byte[] data = new byte[expected];
    int actual = input.read(data);
    if(actual != expected) {
      LOG.warn("Unexpected length {}, expected {}", actual, expected);
    }
    return new Real(signature, major, minor, width, height, data);
  }
}
