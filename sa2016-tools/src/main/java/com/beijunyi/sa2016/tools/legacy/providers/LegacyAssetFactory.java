package com.beijunyi.sa2016.tools.legacy.providers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

public interface LegacyAssetFactory<Asset extends LegacyAsset> {

  Kryo KRYO = KryoFactory.getInstance();

  @Nonnull
  Asset createAsset(Input input);

}
