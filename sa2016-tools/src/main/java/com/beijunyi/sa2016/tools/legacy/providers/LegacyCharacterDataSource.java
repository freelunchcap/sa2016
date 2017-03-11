package com.beijunyi.sa2016.tools.legacy.providers;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.beijunyi.sa2016.tools.legacy.LegacyCharacterData;
import com.beijunyi.sa2016.tools.legacy.LegacyDataProvider;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;

class LegacyCharacterDataSource extends LegacyDataProvider<LegacyCharacterData> {

  private final int animationNum;

  LegacyCharacterDataSource(Path archive, long position, int animationNum) {
    super(archive, position);
    this.animationNum = animationNum;
  }

  @Nonnull
  @Override
  protected LegacyCharacterData read(Kryo kryo, Input input) {
    ImmutableList.Builder<LegacyAnimation> animations = ImmutableList.builder();
    for(int a = 0; a < animationNum; a++) {
      animations.add(kryo.readObject(input, LegacyAnimation.class));
    }
    return new LegacyCharacterData(animations.build());
  }

}
