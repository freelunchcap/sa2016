package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableCollection;

public class AdrnSet {

  private ImmutableCollection<Adrn> adrns;

  public AdrnSet(ImmutableCollection<Adrn> adrns) {
    this.adrns = adrns;
  }

  @Nonnull
  public ImmutableCollection<Adrn> getAdrns() {
    return adrns;
  }

}
