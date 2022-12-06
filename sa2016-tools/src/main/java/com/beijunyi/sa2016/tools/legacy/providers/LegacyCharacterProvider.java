package com.beijunyi.sa2016.tools.legacy.providers;

import com.beijunyi.sa2016.tools.legacy.LegacyResourceType;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

import static com.beijunyi.sa2016.tools.legacy.LegacyResourceType.SPRADRN;

@Singleton
public final class LegacyCharacterProvider extends LegacyAssetProvider<LegacyCharacter> {

  @Inject
  LegacyCharacterProvider(LegacyResourcesProvider resources, LegacyCharacterFactory factory)
      throws IOException {
    super(resources, factory);
  }

  @Nonnull
  @Override
  protected LegacyResourceType resource() {
    return SPRADRN;
  }
}
