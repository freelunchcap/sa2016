package com.beijunyi.sa2016.tools.converters.character;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacyCharacterHeader;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.SPRADRN;

@Singleton
class CharacterLocator {

  private static final Logger LOG = LoggerFactory.getLogger(CharacterLocator.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Map<Integer, LegacyCharacterHeader> lookup;

  @Inject
  public CharacterLocator(LegacyResourcesProvider resources) throws IOException {
    this.lookup = readIndex(resources.getResourceFile(SPRADRN));
  }

  @Nonnull
  Collection<LegacyCharacterHeader> assets() {
    return lookup.values();
  }

  @Nonnull
  private Map<Integer, LegacyCharacterHeader> readIndex(Path file) throws IOException {
    ImmutableMap.Builder<Integer, LegacyCharacterHeader> ret = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    try(Input input = new Input(Files.newInputStream(file))) {
      while(!input.eof()) {
        LegacyCharacterHeader entry = KRYO.readObject(input, LegacyCharacterHeader.class);
        if(keys.add(entry.getId())) {
          ret.put(entry.getId(), entry);
        } else {
          LOG.warn("Duplicate Character Key: " + entry.getId());
        }
      }
    }
    return ret.build();
  }

}
