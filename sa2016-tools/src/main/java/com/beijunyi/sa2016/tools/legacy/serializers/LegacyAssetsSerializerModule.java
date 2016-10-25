package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;

public class LegacyAssetsSerializerModule extends AbstractModule {


  @Override
  protected void configure() {
    registerSerializers(KryoFactory.getInstance());
  }

  private static void registerSerializers(Kryo kryo) {
    kryo.register(Adrn.class, new AdrnSerializer());
    kryo.register(Ls2Map.class, new Ls2MapSerializer());
    kryo.register(PaletColor.class, new PaletColorSerializer());
    kryo.register(Palet.class, new PaletSerializer());
    kryo.register(Real.class, new RealSerializer());
    kryo.register(SprAdrn.class, new SprAdrnSerializer());
    kryo.register(SprFrame.class, new SprFrameSerializer());
    kryo.register(Spr.class, new SprSerializer());
  }

}
