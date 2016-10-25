package com.beijunyi.sa2016.tools.legacy;

import com.beijunyi.sa2016.tools.legacy.serializers.LegacyAssetsSerializerModule;
import com.google.inject.AbstractModule;

public class LegacyAssetsModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new LegacyAssetsSerializerModule());
  }

}
