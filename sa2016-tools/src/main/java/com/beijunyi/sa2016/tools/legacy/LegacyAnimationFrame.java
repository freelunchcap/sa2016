package com.beijunyi.sa2016.tools.legacy;

public class LegacyAnimationFrame {

  private final int image;
  private final int impactAudio;
  private final int dodgeAudio;

  public LegacyAnimationFrame(int image, int impactAudio, int dodgeAudio) {
    this.image = image;
    this.impactAudio = impactAudio;
    this.dodgeAudio = dodgeAudio;
  }

  public int getImage() {
    return image;
  }

  public int getImpactAudio() {
    return impactAudio;
  }

  public int getDodgeAudio() {
    return dodgeAudio;
  }
  
}
