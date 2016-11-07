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
    KryoFactory.register(Adrn.class, new AdrnSerializer());
    KryoFactory.register(Ls2Map.class, new Ls2MapSerializer());
    KryoFactory.register(Palet.class, new PaletSerializer());
    KryoFactory.register(Real.class, new RealSerializer());
    KryoFactory.register(SprAdrn.class, new SprAdrnSerializer());
    KryoFactory.register(Spr.class, new SprSerializer());
  }

}
