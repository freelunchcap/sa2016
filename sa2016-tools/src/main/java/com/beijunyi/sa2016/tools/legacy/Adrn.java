package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class Adrn {

  private final int uid;
  private final long address;
  private final int size;
  private final int xOffset;
  private final int yOffset;
  private final int width;
  private final int height;
  private final int east;
  private final int south;
  private final int path;
  private final String reference;
  private final int map;

  public Adrn(int uid, long address, int size, int xOffset, int yOffset, int width, int height, int east, int south, int path, String reference, int map) {
    this.uid = uid;
    this.address = address;
    this.size = size;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.width = width;
    this.height = height;
    this.east = east;
    this.south = south;
    this.path = path;
    this.reference = reference;
    this.map = map;
  }

  public int getUid() {
    return uid;
  }

  public long getAddress() {
    return address;
  }

  public int getSize() {
    return size;
  }

  public int getXOffset() {
    return xOffset;
  }

  public int getYOffset() {
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

}
