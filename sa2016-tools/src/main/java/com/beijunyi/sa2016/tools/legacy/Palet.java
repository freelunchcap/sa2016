package com.beijunyi.sa2016.tools.legacy;

import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class Palet {

  public static final Pattern PALET_PATTEN = Pattern.compile("^palet_(\\d+)\\.sap", CASE_INSENSITIVE);

  private final ImmutableList<PaletColor> colors;

  public Palet(ImmutableList<PaletColor> colors) {
    this.colors = colors;
  }

  @Nonnull
  public ImmutableList<PaletColor> getColors() {
    return colors;
  }
}
