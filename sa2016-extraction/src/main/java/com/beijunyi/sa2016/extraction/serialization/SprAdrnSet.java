package com.beijunyi.sa2016.extraction.serialization;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SprAdrnSet implements KryoSerializable {

  private Collection<SprAdrn> sprAdrns;

  @Nonnull
  public Collection<SprAdrn> getSprAdrns() {
    return sprAdrns;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    sprAdrns = new ArrayList<SprAdrn>();
    while(!input.eof())
      sprAdrns.add(kryo.readObject(input, SprAdrn.class));
  }
}
