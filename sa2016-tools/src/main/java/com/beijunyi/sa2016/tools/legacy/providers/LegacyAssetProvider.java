package com.beijunyi.sa2016.tools.legacy.providers;

import com.beijunyi.sa2016.tools.legacy.LegacyResourceType;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public abstract class LegacyAssetProvider<Asset extends LegacyAsset> {

  private static final Logger LOG = LoggerFactory.getLogger(LegacyAssetProvider.class);

  private final ImmutableMap<Integer, Asset> lookup;

  LegacyAssetProvider(LegacyResourcesProvider resources, LegacyAssetFactory<Asset> factory)
      throws IOException {
    this.lookup = indexAssets(resources.getResourceFiles(resource()), factory);
  }

  public ImmutableSet<Integer> keys() {
    return lookup.keySet();
  }

  @Nullable
  public Asset get(int id) {
    return lookup.get(id);
  }

  protected abstract LegacyResourceType resource();

  private ImmutableMap<Integer, Asset> indexAssets(
      Set<Path> files, LegacyAssetFactory<Asset> factory) throws IOException {
    ImmutableMap.Builder<Integer, Asset> ret = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    for (Path file : files) {
      try (Input input = new Input(Files.newInputStream(file))) {
        while (!input.end()) {
          Asset asset = factory.createAsset(input);
          if (keys.add(asset.getId())) {
            ret.put(asset.getId(), asset);
          } else {
            LOG.warn("Duplicate {} ID {}", resource().name(), asset.getId());
          }
        }
      }
    }
    return ret.build();
  }
}
