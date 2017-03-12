package com.beijunyi.sa2016.tools.converters;

import java.io.IOException;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.assets.repositories.AssetRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetExtractionTask<LA extends LegacyAsset, A extends GameAsset> implements Supplier<A> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetExtractionTask.class);

  private final int id;
  private final LegacyAssetProvider<LA> provider;
  private final AssetFactory<LA, A> factory;
  private final AssetRepo<A> repo;

  protected AssetExtractionTask(int id, LegacyAssetProvider<LA> provider, AssetFactory<LA, A> factory, AssetRepo<A> repo) {
    this.id = id;
    this.provider = provider;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public A get() {
    A asset = repo.get(id);
    if(asset == null) {
      LA legacy = provider.get(id);
      try {
        asset = factory.create(legacy);
      } catch(IOException e) {
        LOG.error("Could not create {}", id);
        throw new IllegalStateException(e);
      }
      repo.put(asset);
    }
    return asset;
  }

}
