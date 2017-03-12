package com.beijunyi.sa2016.tools.converters;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.assets.repositories.AssetRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;

public class AssetExtractionTaskFactory<LA extends LegacyAsset, A extends GameAsset> {

  private final LegacyAssetProvider<LA> provider;
  private final AssetFactory<LA, A> factory;
  private final AssetRepo<A> repo;

  public AssetExtractionTaskFactory(LegacyAssetProvider<LA> provider, AssetFactory<LA, A> factory, AssetRepo<A> repo) {
    this.provider = provider;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  public AssetExtractionTask<LA, A> newExtraction(int id) {
    return new AssetExtractionTask<>(id, provider, factory, repo);
  }

}
