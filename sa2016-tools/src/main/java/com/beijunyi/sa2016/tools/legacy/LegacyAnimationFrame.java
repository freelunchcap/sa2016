package com.beijunyi.sa2016.tools.legacy;

public class LegacyAnimationFrame {

  private final int sprite;
  private final int impactAudio;
  private final int dodgeAudio;

  public LegacyAnimationFrame(int sprite, int impactAudio, int dodgeAudio) {
    this.sprite = sprite;
    this.impactAudio = impactAudio;
    this.dodgeAudio = dodgeAudio;
  }

  public int getSprite() {
    return sprite;
  }

  public int getImpactAudio() {
    return impactAudio;
  }

  public int getDodgeAudio() {
    return dodgeAudio;
  }
  
}
