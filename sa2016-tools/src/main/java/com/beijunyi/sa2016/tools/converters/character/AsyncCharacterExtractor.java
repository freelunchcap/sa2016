package com.beijunyi.sa2016.tools.converters.character;

import com.beijunyi.sa2016.assets.Avatar;
import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.tools.converters.AsyncAssetExtractor;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacter;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacterProvider;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AsyncCharacterExtractor extends AsyncAssetExtractor<LegacyCharacter, Avatar>
    implements AssetExtractor {

  @Inject
  AsyncCharacterExtractor(LegacyCharacterProvider provider, CharacterExtractionTaskFactory tasks) {
    super(provider, tasks);
  }

  @Nonnull
  @Override
  public String name() {
    return "character";
  }
}
