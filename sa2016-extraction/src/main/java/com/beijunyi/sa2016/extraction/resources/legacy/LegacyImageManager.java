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

import static com.beijunyi.sa2016.extraction.resources.legacy.LegacyResource.ADRN;

public class LegacyImageManager {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Adrn> adrnMap;
  private Map<Integer, Set<Integer>> floorElementsMap;

  @Inject
  public LegacyImageManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  public int count() throws IOException {
    indexContents();
    return adrnMap.size();
  }

  private void indexContents() throws IOException {
    if(adrnMap == null || floorElementsMap == null) {
      AdrnSet artifacts = readArtifact();
      adrnMap = new HashMap<>();
      floorElementsMap = new HashMap<>();
      for(Adrn adrn : artifacts.getAdrns()) {
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
  private AdrnSet readArtifact() throws IOException {
    Path file = finder.findUnique(ADRN);
    try(InputStream stream = Files.newInputStream(file)) {
      return kryo.readObject(new Input(stream), AdrnSet.class);
    }
  }



}
