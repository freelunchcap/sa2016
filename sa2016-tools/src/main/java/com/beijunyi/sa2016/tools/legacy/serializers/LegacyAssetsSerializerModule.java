package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.*;
import com.google.inject.AbstractModule;

import static com.beijunyi.sa2016.utils.KryoFactory.register;

public class LegacyAssetsSerializerModule extends AbstractModule {


  @Override
  protected void configure() {
    registerSerializers();
  }

  private static void registerSerializers() {
    register(LegacySpriteHeader.class, new LegacySpriteHeaderSerializer());
    register(Ls2Map.class, new Ls2MapSerializer());
    register(LegacyPalet.class, new PaletSerializer());
    register(LegacySpriteData.class, new LegacySpriteDataSerializer());
    register(LegacyCharacterHeader.class, new LegacyCharacterHeaderSerializer());
    register(LegacyAnimation.class, new LegacyAnimationSerializer());
    register(LegacyAnimationFrame.class, new LegacyAnimationFrameSerializer());
  }

}
