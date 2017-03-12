package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyResourceType;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.LegacyResourceType.SPRADRN;

@Singleton
public class LegacyCharacterProvider extends LegacyAssetProvider<LegacyCharacter> {

  @Inject
  LegacyCharacterProvider(LegacyResourcesProvider resources, LegacyCharacterFactory factory) throws IOException {
    super(resources, factory);
  }

  @Nonnull
  @Override
  protected LegacyResourceType resource() {
    return SPRADRN;
  }

}
