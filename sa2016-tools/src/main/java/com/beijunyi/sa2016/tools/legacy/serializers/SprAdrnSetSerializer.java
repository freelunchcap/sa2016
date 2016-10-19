package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public class SprAdrnSetSerializer extends Serializer<SprAdrnSet> {

  @Override
  public void write(Kryo kryo, Output output, SprAdrnSet object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public SprAdrnSet read(Kryo kryo, Input input, Class<SprAdrnSet> type) {
    ImmutableCollection.Builder<SprAdrn> adrns = ImmutableList.builder();
    while(!input.eof())
      adrns.add(kryo.readObject(input, SprAdrn.class));
    return new SprAdrnSet(adrns.build());
  }
}
