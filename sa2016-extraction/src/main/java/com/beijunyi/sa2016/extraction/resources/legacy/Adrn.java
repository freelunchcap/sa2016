package com.beijunyi.sa2016.extraction.resources.legacy;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.extraction.utils.IntegerReader.LE;

public class Adrn implements KryoSerializable {

  private int uid;
  private long address;
  private int size;
  private int xOffset;
  private int yOffset;
  private int width;
  private int height;
  private int east;
  private int south;
  private int path;
  private String reference;
  private int map;

  public int getUid() {
    return uid;
  }

  public long getAddress() {
    return address;
  }

  public int getSize() {
    return size;
  }

  public int getxOffset() {
    return xOffset;
  }

  public int getyOffset() {
    return yOffset;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getEast() {
    return east;
  }

  public int getSouth() {
    return south;
  }

  public int getPath() {
    return path;
  }

  @Nonnull
  public String getReference() {
    return reference;
  }

  public int getMapId() {
    return map;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    uid = LE.int32(input);
    address = LE.uint32(input);
    size = LE.int32(input);
    xOffset = LE.int32(input);
    yOffset = LE.int32(input);
    width = LE.int32(input);
    height = LE.int32(input);
    east = LE.uint8(input);
    south = LE.uint8(input);
    path = LE.uint8(input);
    reference = new String(input.readBytes(45));
    map = LE.int32(input);
  }

}
