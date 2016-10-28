package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import java.awt.*;

public class Palet {

  private final ImmutableList<Color> colors;

  public Palet(ImmutableList<Color> colors) {
    this.colors = colors;
  }

  @Nonnull
  public ImmutableList<Color> getColors() {
    return colors;
  }
}
