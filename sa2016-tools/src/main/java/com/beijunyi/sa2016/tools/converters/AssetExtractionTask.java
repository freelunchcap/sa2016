package com.beijunyi.sa2016.tools.converters;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.assets.repositories.AssetRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public final class AssetExtractionTask<LA extends LegacyAsset, A extends GameAsset> {

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

  public ImmutableList<A> extract() throws IOException {
    ImmutableList<A> assets = repo.get(id);
    if (assets.isEmpty()) {
      List<LA> legacyAssets = provider.get(id);
      ImmutableList.Builder<A> builder = ImmutableList.builder();
      for (LA l : legacyAssets) {
        A asset = factory.create(l);
        repo.put(asset);
        builder.add(asset);
      }
      assets = builder.build();
    }
    return assets;
  }
}
