package com.beijunyi.sa2016.tools.legacy;

public class SprAdrn {

  private final int id;
  private final long address;
  private final int actions;
  private final int sound;

  public SprAdrn(int id, long address, int actions, int sound) {
    this.id = id;
    this.address = address;
    this.actions = actions;
    this.sound = sound;
  }

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

}
