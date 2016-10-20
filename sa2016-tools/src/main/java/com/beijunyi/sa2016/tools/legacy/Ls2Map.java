package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

public class Ls2Map {

  private final int id;
  private final String name;
  private final int east;
  private final int south;
  private final int[] tiles;
  private final int[] objects;

  public Ls2Map(int id, String name, int east, int south, int[] tiles, int[] objects) {
    this.id = id;
    this.name = name;
    this.east = east;
    this.south = south;
    this.tiles = tiles;
    this.objects = objects;
  }

  public int getId() {
    return id;
  }

  @Nonnull
  public String getName() {
    return name;
  }

  public int getEast() {
    return east;
  }

  public int getSouth() {
    return south;
  }

  @Nonnull
  public int[] getTiles() {
    return tiles;
  }

  @Nonnull
  public int[] getObjects() {
    return objects;
  }

}
