package com.beijunyi.sa2016.tools.converters.character;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.repositories.CharacterRepo;
import com.beijunyi.sa2016.tools.converters.AssetExtractionTaskFactory;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacter;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyCharacterProvider;

@Singleton
class CharacterExtractionTaskFactory extends AssetExtractionTaskFactory<LegacyCharacter, Character> {

  @Inject
  CharacterExtractionTaskFactory(LegacyCharacterProvider provider, CharacterFactory factory, CharacterRepo repo) {
    super(provider, factory, repo);
  }

}
