package com.beijunyi.sa2016.tools.converters.character;

import com.beijunyi.sa2016.assets.Avatar;
import com.beijunyi.sa2016.assets.repositories.AvatarRepo;
import com.beijunyi.sa2016.tools.converters.AssetExtractionTaskFactory;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacter;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class CharacterExtractionTaskFactory extends AssetExtractionTaskFactory<LegacyCharacter, Avatar> {

  @Inject
  CharacterExtractionTaskFactory(
      LegacyCharacterProvider provider, CharacterFactory factory, AvatarRepo repo) {
    super(provider, factory, repo);
  }
}
