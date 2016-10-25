package com.beijunyi.sa2016.utils;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;

public class KryoFactory {

  @Nonnull
  public static Kryo getInstance() {
    return Holder.INSTANCE;
  }

  private static class Holder {

    private static final Kryo INSTANCE = createInstance();

    @Nonnull
    private static Kryo createInstance() {
      Kryo kryo = new Kryo();
      kryo.setReferences(false);
      return kryo;
    }
  }

}
