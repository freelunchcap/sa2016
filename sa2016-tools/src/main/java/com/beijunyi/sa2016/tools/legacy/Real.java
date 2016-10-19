package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.utils.IntegerReader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class Real implements KryoSerializable {

  private String magic;
  private int major;
  private int minor;
  private int width;
  private int height;
  private byte[] data;

  @Nonnull
  public String getMagic() {
    return magic;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Nonnull
  public byte[] getData() {
    return data;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    magic = new String(input.readBytes(2));
    major = LE.uint8(input);
    minor = LE.uint8(input);
    width = (int) LE.uint32(input);
    height = (int) LE.uint32(input);
    if(major == 1) {
      data = new byte[(int) LE.uint32(input)];
      if(input.read(data) != data.length)
        throw new IllegalStateException();
    } else {
      input.skip(4);
      data = new byte[width * height];
      if(input.read(data) != data.length)
        throw new IllegalStateException();
    }
  }

}
