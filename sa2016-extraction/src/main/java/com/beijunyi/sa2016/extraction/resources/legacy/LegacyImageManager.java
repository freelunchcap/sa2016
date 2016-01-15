package com.beijunyi.sa2016.extraction.resources.legacy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
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
    loadArtifacts();
    return adrnMap.size();
  }

  private void loadArtifacts() throws IOException {
    if(adrnMap == null || floorElementsMap == null) {
      Path adrn = finder.findUnique(ADRN);
    }
  }



}
