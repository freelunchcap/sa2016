package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.AdrnSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public class AdrnSetSerializer extends Serializer<AdrnSet> {

  @Override
  public void write(Kryo kryo, Output output, AdrnSet object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public AdrnSet read(Kryo kryo, Input input, Class<AdrnSet> type) {
    ImmutableCollection.Builder<Adrn> adrns = ImmutableList.builder();
    while(!input.eof())
      adrns.add(kryo.readObject(input, Adrn.class));
    return new AdrnSet(adrns.build());
  }
}
