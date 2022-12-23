package com.beijunyi.sa2016.tools.converters;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.assets.repositories.AssetRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static org.slf4j.LoggerFactory.getLogger;

public final class AssetExtractionTask<LA extends LegacyAsset, A extends GameAsset>
    implements Supplier<ImmutableList<A>> {

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

  @Override
  public ImmutableList<A> get() {
    ImmutableList<A> assets = repo.get(id);
    if (assets.isEmpty()) {
      ImmutableList<A> newAssets =
          provider.get(id).stream()
              .map(
                  legacy -> {
                    try {
                      return factory.create(legacy);
                    } catch (java.io.IOException e) {
                      throw new RuntimeException(e);
                    }
                  })
              .collect(toImmutableList());
      newAssets.forEach(repo::put);
      assets = newAssets;
    }
    return assets;
  }
}
