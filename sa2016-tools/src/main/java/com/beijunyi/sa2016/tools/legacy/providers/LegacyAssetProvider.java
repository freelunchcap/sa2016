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
public class LegacyAssetProvider<Asset extends LegacyAsset> {

  private static final Logger LOG = LoggerFactory.getLogger(LegacyAssetProvider.class);
  private static final Kryo KRYO = KryoFactory.getInstance();


  private final Map<Integer, Asset> lookup;

  @Inject
  LegacyAssetProvider(ResourcesProvider resources, LegacyCharacterFactory factory) throws IOException {
    this.lookup = indexCharacters(resources.getClientResource(ADRN), factory);
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

  protected abstract LegacyResource

  @Nonnull
  private static Map<Integer, LegacyCharacter> indexCharacters(Path adrn, LegacyCharacterFactory factory) throws IOException {
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
