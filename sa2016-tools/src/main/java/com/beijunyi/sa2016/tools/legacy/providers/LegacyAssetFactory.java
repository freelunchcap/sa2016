package com.beijunyi.sa2016.tools.legacy.providers;

import javax.annotation.Nonnull;

public interface LegacyAssetFactory<Input, Output extends LegacyAsset> {

  @Nonnull
  Output createAsset(Input input);

}
