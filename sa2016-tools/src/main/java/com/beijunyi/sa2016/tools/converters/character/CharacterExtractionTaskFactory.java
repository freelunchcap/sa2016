package com.beijunyi.sa2016.tools.converters.character;

import javax.inject.Inject;

import com.beijunyi.sa2016.assets.repositories.CharacterRepo;
import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.SPR;

class CharacterExtractionTaskFactory {

  private final LegacyResourcesProvider resources;
  private final CharacterFactory characters;
  private final CharacterRepo repo;

  @Inject
  public CharacterExtractionTaskFactory(LegacyResourcesProvider resources, CharacterFactory characters, CharacterRepo repo) {
    this.resources = resources;
    this.characters = characters;
    this.repo = repo;
  }

  CharacterExtractionTask newExtraction(LegacyCharacterHeader entry) {
    return new CharacterExtractionTask(resources.getResourceFile(SPR), entry, characters, repo);
  }

}
