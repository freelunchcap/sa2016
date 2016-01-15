package com.beijunyi.sa2016.extraction.serialization;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.extraction.utils.IntegerReader.LE;

public class SprAdrn implements KryoSerializable {

  private int id;
  private long address;
  private int actions;
  private int sound;

  public int getId() {
    return id;
  }

  public long getAddress() {
    return address;
  }

  public int getActions() {
    return actions;
  }

  public int getSound() {
    return sound;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(Kryo kryo, Input input) {
    id = (int) LE.uint32(input);
    address = LE.uint32(input);
    actions = LE.uint16(input);
    sound = LE.uint16(input);
  }

}
