package com.beijunyi.sa2016.tools.converters.characters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.tools.legacy.SprAdrn;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.SPRADRN;

@Singleton
class CharacterLocator {

  private static final Logger LOG = LoggerFactory.getLogger(CharacterLocator.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Map<Integer, SprAdrn> lookup;

  @Inject
  public CharacterLocator(ResourcesProvider resources) throws IOException {
    this.lookup = readIndex(resources.getClientResource(SPRADRN));
  }

  @Nonnull
  Collection<SprAdrn> assets() {
    return lookup.values();
  }

  @Nonnull
  private Map<Integer, SprAdrn> readIndex(Path file) throws IOException {
    ImmutableMap.Builder<Integer, SprAdrn> ret = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    try(Input input = new Input(Files.newInputStream(file))) {
      while(!input.eof()) {
        SprAdrn entry = KRYO.readObject(input, SprAdrn.class);
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
