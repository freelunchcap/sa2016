package com.beijunyi.sa2016.extraction.resources.legacy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.context.EnvironmentService;
import com.esotericsoftware.kryo.Kryo;

public class LegacyImageManager {

  private final Kryo kryo;
  private final EnvironmentService environmentService;

  private Map<Integer, Adrn> adrnMap;
  private Map<Integer, Set<Integer>> floorElementsMap = new HashMap<Integer, Set<Integer>>();

  @Inject
  public LegacyImageManager(@Nonnull Kryo kryo, @Nonnull EnvironmentService environmentService) {
    this.kryo = kryo;
    this.environmentService = environmentService;
  }

  private void loadArtifacts() {
    if(adrnMap == null) {

    }
  }

}
