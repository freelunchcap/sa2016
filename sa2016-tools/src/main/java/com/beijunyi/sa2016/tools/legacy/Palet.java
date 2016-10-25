package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

public class Palet {

  private final ImmutableList<PaletColor> colors;

  public Palet(ImmutableList<PaletColor> colors) {
    this.colors = colors;
  }

  @Nonnull
  public ImmutableList<PaletColor> getColors() {
    return colors;
  }
}
