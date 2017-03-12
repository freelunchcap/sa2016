package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyResource;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.LegacyResource.ADRN;

@Singleton
public class LegacySpriteProvider extends LegacyAssetProvider<LegacySprite> {

  @Inject
  LegacySpriteProvider(LegacyResourcesProvider resources, LegacySpriteFactory factory) throws IOException {
    super(resources, factory);
  }

  @Nonnull
  @Override
  protected LegacyResource resource() {
    return ADRN;
  }

}
