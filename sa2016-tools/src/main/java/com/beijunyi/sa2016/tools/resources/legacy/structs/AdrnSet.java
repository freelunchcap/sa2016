package com.beijunyi.sa2016.tools.resources.legacy.structs;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class AdrnSet implements KryoSerializable {

  private Collection<Adrn> adrns;

  @Nonnull
  public Collection<Adrn> getAdrns() {
    return adrns;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    adrns = new ArrayList<>();
    while(!input.eof())
      adrns.add(kryo.readObject(input, Adrn.class));
  }
}
