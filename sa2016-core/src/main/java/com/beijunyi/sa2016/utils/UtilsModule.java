package com.beijunyi.sa2016.utils;

import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;

public class UtilsModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Kryo.class).toInstance(prepareKryo());
  }

  private static Kryo prepareKryo() {
    Kryo kryo = new Kryo();
    kryo.setReferences(false);
    return kryo;
  }
}
