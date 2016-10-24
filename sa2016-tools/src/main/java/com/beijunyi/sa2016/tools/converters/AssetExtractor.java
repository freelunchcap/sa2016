package com.beijunyi.sa2016.tools.converters;

import javax.annotation.Nonnull;

public interface AssetExtractor {

  @Nonnull
  String name();

  void extract();

}
