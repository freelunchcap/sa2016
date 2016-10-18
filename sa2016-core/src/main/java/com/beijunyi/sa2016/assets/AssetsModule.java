package com.beijunyi.sa2016.assets;

import com.beijunyi.sa2016.assets.repository.AssetRepositoryModule;
import com.beijunyi.sa2016.assets.serializers.AssetSerializersModule;
import com.google.inject.AbstractModule;

public class AssetsModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new AssetRepositoryModule());
    install(new AssetSerializersModule());
  }

}
