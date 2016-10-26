package com.beijunyi.sa2016.tools.converters;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.ADRN;
import static com.beijunyi.sa2016.tools.legacy.ClientResource.REAL;
import static java.nio.file.StandardOpenOption.READ;

@Singleton
class ImageLocator {

  private static final Logger LOG = LoggerFactory.getLogger(ImageLocator.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final ImmutableMap<Integer, Adrn> adrns;
  private final ImmutableMap<Integer, Integer> tileIdMap;

  @Inject
  public ImageLocator(ResourcesProvider resources) throws IOException {
    this.adrns = readAdrns(resources.getClientResource(ADRN));
    this.tileIdMap = indexTiles(adrns.values());
  }

  @Nonnull
  public ImmutableCollection<Adrn> imageAssets() {
    return adrns.values();
  }

  @Nullable
  public Adrn getTileImage(int tileId) {
    int imageId = tileIdMap.get(tileId);
    return adrns.get(imageId);
  }

  @Nonnull
  private static ImmutableMap<Integer, Adrn> readAdrns(Path file) throws IOException {
    ImmutableMap.Builder<Integer, Adrn> adrns = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    try(Input input = new Input(Files.newInputStream(file))) {
      while(!input.eof()) {
        Adrn adrn = KRYO.readObject(input, Adrn.class);
        if(keys.add(adrn.getUid())) {
          adrns.put(adrn.getUid(), adrn);
        } else {
          LOG.warn("Duplicate Image Key: " + adrn.getUid());
        }
      }
    }
    return adrns.build();
  }

  @Nonnull
  private static ImmutableMap<Integer, Integer> indexTiles(Collection<Adrn> adrns) {
    ImmutableMap.Builder<Integer, Integer> builder = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    for(Adrn adrn : adrns) {
      if(adrn.getMapId() == 0) continue;
      if(keys.add(adrn.getMapId())) {
        builder.put(adrn.getMapId(), adrn.getUid());
      } else {
        LOG.warn("Duplicate Tile Key: " + adrn.getMapId());
      }
    }
    return builder.build();
  }

}
