package com.beijunyi.sa2016.tools.converters.sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.ADRN;

@Singleton
class SpriteAssetsManager {

  private static final Logger LOG = LoggerFactory.getLogger(SpriteAssetsManager.class);
  private static final Kryo KRYO = KryoFactory.getInstance();


  private final Map<Integer, SpriteAsset> lookup;

  @Inject
  SpriteAssetsManager(ResourcesProvider resources, SpriteAssetFactory factory) throws IOException {
    this.lookup = indexSpriteAssets(resources.getClientResource(ADRN), factory);
  }

  @Nonnull
  Set<Integer> keys() {
    return lookup.keySet();
  }

  @Nonnull
  SpriteAsset get(int key) {
    SpriteAsset ret = lookup.get(key);
    if(ret == null) throw new IllegalStateException();
    return lookup.get(key);
  }

  @Nonnull
  private static Map<Integer, SpriteAsset> indexSpriteAssets(Path adrn, SpriteAssetFactory factory) throws IOException {
    ImmutableMap.Builder<Integer, SpriteAsset> adrns = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    try(Input input = new Input(Files.newInputStream(adrn))) {
      while(!input.eof()) {
        SpriteAsset asset = factory.createAsset(KRYO.readObject(input, Adrn.class));
        if(keys.add(asset.getId())) {
          adrns.put(asset.getId(), asset);
        } else {
          LOG.warn("Duplicate Sprite Key: " + asset.getId());
        }
      }
    }
    return adrns.build();
  }

}
