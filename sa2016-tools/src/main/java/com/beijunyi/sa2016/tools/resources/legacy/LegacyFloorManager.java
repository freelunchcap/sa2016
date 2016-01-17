package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.structs.Ls2Map;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResource.LS2MAP;
import static java.util.Collections.unmodifiableCollection;

public class LegacyFloorManager {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Ls2Map> floorMap;

  @Inject
  public LegacyFloorManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  public int count() throws IOException {
    indexResources();
    return floorMap.size();
  }

  @Nonnull
  public Collection<Ls2Map> getAllFloors() throws IOException {
    indexResources();
    return unmodifiableCollection(floorMap.values());
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
