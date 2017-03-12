package com.beijunyi.sa2016.tools.converters;

import java.io.IOException;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;

public interface AssetFactory<LA extends LegacyAsset, A extends GameAsset> {

  @Nonnull
  A create(LA legacy) throws IOException;

}
