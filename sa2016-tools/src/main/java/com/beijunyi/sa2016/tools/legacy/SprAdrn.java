package com.beijunyi.sa2016.tools.legacy;

public class SprAdrn {

  private final int id;
  private final long address;
  private final int animations;
  private final int sound;

  public SprAdrn(int id, long address, int animations, int sound) {
    this.id = id;
    this.address = address;
    this.animations = animations;
    this.sound = sound;
  }

  public int getId() {
    return id;
  }

  public long getAddress() {
    return address;
  }

  public int getAnimations() {
    return animations;
  }

  public int getSound() {
    return sound;
  }

}
