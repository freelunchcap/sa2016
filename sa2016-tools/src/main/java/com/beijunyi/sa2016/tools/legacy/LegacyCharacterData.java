package com.beijunyi.sa2016.tools.legacy;

import java.util.Collection;
import javax.annotation.Nonnull;

public class LegacyCharacterData {

  private final Collection<LegacyAnimation> animations;

  public LegacyCharacterData(Collection<LegacyAnimation> animations) {
    this.animations = animations;
  }

  @Nonnull
  public Collection<LegacyAnimation> getAnimations() {
    return animations;
  }

}
