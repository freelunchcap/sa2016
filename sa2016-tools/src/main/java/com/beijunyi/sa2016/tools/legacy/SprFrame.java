package com.beijunyi.sa2016.tools.legacy;

public class SprFrame {

  private final int image;
  private final int unknown;
  private final int impactAudio;
  private final int dodgeAudio;

  public SprFrame(int image, int unknown, int impactAudio, int dodgeAudio) {
    this.image = image;
    this.unknown = unknown;
    this.impactAudio = impactAudio;
    this.dodgeAudio = dodgeAudio;
  }

  public int getImage() {
    return image;
  }

  public int getUnknown() {
    return unknown;
  }

  public int getImpactAudio() {
    return impactAudio;
  }

  public int getDodgeAudio() {
    return dodgeAudio;
  }

}
