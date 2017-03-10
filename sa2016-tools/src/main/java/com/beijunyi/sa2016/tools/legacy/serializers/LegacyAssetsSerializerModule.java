package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.google.inject.AbstractModule;

public class LegacyAssetsSerializerModule extends AbstractModule {


  @Override
  protected void configure() {
    registerSerializers();
  }

  private static void registerSerializers() {
    KryoFactory.register(LegacySpriteHeader.class, new LegacySpriteHeaderSerializer());
    KryoFactory.register(Ls2Map.class, new Ls2MapSerializer());
    KryoFactory.register(LegacyPalet.class, new PaletSerializer());
    KryoFactory.register(LegacySpriteData.class, new LegacySpriteDataSerializer());
    KryoFactory.register(LegacyCharacterHeader.class, new LegacyCharacterHeaderSerializer());
    KryoFactory.register(LegacyAnimation.class, new LegacyAnimationSerializer());
  }

}
