package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.ResourceManager;
import com.beijunyi.sa2016.tools.resources.ResourceType;
import com.beijunyi.sa2016.tools.resources.legacy.structs.Ls2Map;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResourceFile.LS2MAP;
import static com.beijunyi.sa2016.tools.resources.ResourceType.LEGACY_FLOOR;
import static java.util.Collections.unmodifiableCollection;

public class LegacyFloorManager implements ResourceManager<Ls2Map> {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Ls2Map> floorMap;

  @Inject
  public LegacyFloorManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  @Nonnull
  @Override
  public ResourceType getResourceType() {
    return LEGACY_FLOOR;
  }

  @Override
  public int count() throws IOException {
    indexResources();
    return floorMap.size();
  }

  @Nonnull
  @Override
  public Ls2Map getResource(int id) throws IOException {
    indexResources();
    return floorMap.get(id);
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
      try(Input input = new Input(Files.newInputStream(file))) {
        ret.add(kryo.readObject(input, Ls2Map.class));
      }
    }
    return ret;
  }
}
