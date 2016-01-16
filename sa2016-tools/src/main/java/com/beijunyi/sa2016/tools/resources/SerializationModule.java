package com.beijunyi.sa2016.tools.resources;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;

public class SerializationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Kryo.class).toInstance(setupKryo());
  }

  @Nonnull
  private static Kryo setupKryo() {
    Kryo ret = new Kryo();
    ret.setReferences(false);
    return ret;
  }

}
