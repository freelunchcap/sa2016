package com.beijunyi.sa2016.tools.converters;

import com.beijunyi.sa2016.assets.Asset;
import com.beijunyi.sa2016.assets.repositories.AssetRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import org.slf4j.Logger;

import java.io.IOException;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public final class AssetExtractionTask<LA extends LegacyAsset, A extends Asset> {

  private static final Logger LOG = getLogger(AssetExtractionTask.class);

  private final int id;
  private final LegacyAssetProvider<LA> provider;
  private final AssetFactory<LA, A> factory;
  private final AssetRepo<A> repo;

  AssetExtractionTask(
      int id, LegacyAssetProvider<LA> provider, AssetFactory<LA, A> factory, AssetRepo<A> repo) {
    this.id = id;
    this.provider = provider;
    this.factory = factory;
    this.repo = repo;
  }

  public A extract() throws IOException {
    A cached = repo.get(id);
    if (cached == null) {
      LA legacyAssets = provider.get(id);
      if (legacyAssets == null) {
        throw new IllegalStateException(format("No asset found %d", id));
      }

      A asset = factory.create(legacyAssets);
      repo.put(asset);
      cached = asset;
    }
    return cached;
  }
}
