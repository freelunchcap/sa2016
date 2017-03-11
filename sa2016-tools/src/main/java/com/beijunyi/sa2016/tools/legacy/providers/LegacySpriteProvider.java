package com.beijunyi.sa2016.tools.legacy.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.ADRN;

@Singleton
public class LegacySpriteProvider {

  private static final Logger LOG = LoggerFactory.getLogger(LegacySpriteProvider.class);
  private static final Kryo KRYO = KryoFactory.getInstance();


  private final Map<Integer, LegacySprite> lookup;

  @Inject
  LegacySpriteProvider(ResourcesProvider resources, LegacySpriteFactory factory) throws IOException {
    this.lookup = indexSprites(resources.getClientResource(ADRN), factory);
  }

  @Nonnull
  public Set<Integer> keys() {
    return lookup.keySet();
  }

  @Nonnull
  public LegacySprite get(int key) {
    LegacySprite ret = lookup.get(key);
    if(ret == null) throw new IllegalStateException();
    return lookup.get(key);
  }

  @Nonnull
  private static Map<Integer, LegacySprite> indexSprites(Path adrn, LegacySpriteFactory factory) throws IOException {
    ImmutableMap.Builder<Integer, LegacySprite> adrns = ImmutableMap.builder();
    Set<Integer> keys = new HashSet<>();
    try(Input input = new Input(Files.newInputStream(adrn))) {
      while(!input.eof()) {
        LegacySprite asset = factory.createAsset(KRYO.readObject(input, LegacySpriteHeader.class));
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
