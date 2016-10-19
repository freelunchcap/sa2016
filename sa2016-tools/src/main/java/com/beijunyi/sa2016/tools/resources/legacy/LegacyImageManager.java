package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.tools.resources.ResourceManager;
import com.beijunyi.sa2016.tools.resources.ResourceType;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResourceFile.*;
import static com.beijunyi.sa2016.tools.resources.ResourceType.LEGACY_IMAGE;
import static java.nio.file.StandardOpenOption.READ;
import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;

public class LegacyImageManager implements ResourceManager<LegacyImageObject> {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Adrn> adrnMap;
  private Map<Integer, Set<Integer>> floorElementsMap;
  private Path real;

  @Inject
  public LegacyImageManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  @Nonnull
  @Override
  public ResourceType getResourceType() {
    return LEGACY_IMAGE;
  }

  @Override
  public int count() throws IOException {
    indexResources();
    return adrnMap.size();
  }

  @Nonnull
  @Override
  public LegacyImageObject getResource(int id) throws IOException {
    indexResources();
    Adrn adrn = adrnMap.get(id);
    Real real = readRealData(adrn);
    return new LegacyImageObject(adrn , real);
  }

  @Nonnull
  public Set<Integer> getMappedImageIds(int floorElementId) throws IOException {
    indexResources();
    Set<Integer> ret = floorElementsMap.get(floorElementId);
    if(ret == null)
      return emptySet();
    return unmodifiableSet(ret);
  }

  private void indexResources() throws IOException {
    if(adrnMap == null || floorElementsMap == null) {
      AdrnSet resources = openAdrn();
      real = finder.findUnique(REAL);
      adrnMap = new HashMap<>();
      floorElementsMap = new HashMap<>();
      for(Adrn adrn : resources.getAdrns()) {
        int uid = adrn.getUid();
        int mapId = adrn.getMapId();
        adrnMap.put(uid, adrn);
        if(mapId != 0) {
          Set<Integer> uids = floorElementsMap.get(mapId);
          if(uids == null) {
            uids = new HashSet<>();
            floorElementsMap.put(mapId, uids);
          }
          uids.add(uid);
        }
      }
    }
  }

  @Nonnull
  private AdrnSet openAdrn() throws IOException {
    Path adrn = finder.findUnique(ADRN);
    try(Input input = new Input(Files.newInputStream(adrn))) {
      return kryo.readObject(input, AdrnSet.class);
    }
  }

  @Nonnull
  private Real readRealData(@Nonnull Adrn adrn) throws IOException {
    FileChannel channel = FileChannel.open(real, READ);
    channel.position(adrn.getAddress());
    try(Input input = new Input(Channels.newInputStream(channel))) {
      return kryo.readObject(input, Real.class);
    }
  }



}
