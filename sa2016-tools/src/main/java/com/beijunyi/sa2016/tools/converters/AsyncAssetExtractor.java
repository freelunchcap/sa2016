package com.beijunyi.sa2016.tools.converters;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;

public abstract class AsyncAssetExtractor<LA extends LegacyAsset, A extends GameAsset> implements AssetExtractor {

  private final LegacyAssetProvider<LA> provider;
  private final AssetExtractionTaskFactory<LA, A> tasks;

  protected AsyncAssetExtractor(LegacyAssetProvider<LA> provider, AssetExtractionTaskFactory<LA, A> tasks) {
    this.provider = provider;
    this.tasks = tasks;
  }

  @Nonnull
  public CompletableFuture<A> genAsset(int id) {
    return CompletableFuture.supplyAsync(tasks.newExtraction(id));
  }

  @Override
  public void extract() {
    provider.keys()
      .forEach(this::genAsset);
  }

}
