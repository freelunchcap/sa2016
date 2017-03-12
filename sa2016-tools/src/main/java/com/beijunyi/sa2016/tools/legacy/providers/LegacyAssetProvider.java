package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.LegacyResourceType;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LegacyAssetProvider<Asset extends LegacyAsset> {

  private static final Logger LOG = LoggerFactory.getLogger(LegacyAssetProvider.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Map<Integer, Asset> lookup;

  LegacyAssetProvider(LegacyResourcesProvider resources, LegacyAssetFactory<Asset> factory) throws IOException {
    this.lookup = indexAssets(resources.getResourceFiles(resource()), factory);
  }

  @Nonnull
  public Set<Integer> keys() {
    return lookup.keySet();
  }

  @Nonnull
  public Asset get(int id) {
    Asset ret = lookup.get(id);
    if(ret == null) throw new IllegalStateException();
    return ret;
  }

  @Nonnull
  protected abstract LegacyResourceType resource();

  @Nonnull
  private Map<Integer, Asset> indexAssets(Collection<Path> files, LegacyAssetFactory<Asset> factory) throws IOException {
    ImmutableMap.Builder<Integer, Asset> ret = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    for(Path file : files) {
      try(Input input = new Input(Files.newInputStream(file))) {
        while(!input.eof()) {
          Asset asset = factory.createAsset(input);
          if(keys.add(asset.getId())) {
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
