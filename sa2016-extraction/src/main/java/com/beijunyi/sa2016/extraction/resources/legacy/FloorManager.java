package com.beijunyi.sa2016.extraction.resources.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResource.LS2MAP;

public class FloorManager {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Ls2Map> floorMap;

  @Inject
  public FloorManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  public int count() throws IOException {
    indexResources();
    return floorMap.size();
  }

  private void indexResources() throws IOException {
    if(floorMap == null) {
      floorMap = new HashMap<>();
      Collection<Ls2Map> resources = readResources();
      for(Ls2Map map : resources) {
        floorMap.put(map.getId(), map);
      }
    }
  }

  @Nonnull
  private Collection<Ls2Map> readResources() throws IOException {
    Collection<Ls2Map> ret = new ArrayList<>();
    Set<Path> files = finder.find(LS2MAP);
    for(Path file : files) {
      try(InputStream stream = Files.newInputStream(file)) {
        ret.add(kryo.readObject(new Input(stream), Ls2Map.class));
      }
    }
    return ret;
  }
}
