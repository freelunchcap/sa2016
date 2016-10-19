package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableCollection;

public class SprAdrnSet {

  private final ImmutableCollection<SprAdrn> sprAdrns;

  public SprAdrnSet(ImmutableCollection<SprAdrn> sprAdrns) {
    this.sprAdrns = sprAdrns;
  }

  @Nonnull
  public ImmutableCollection<SprAdrn> getSprAdrns() {
    return sprAdrns;
  }

}
