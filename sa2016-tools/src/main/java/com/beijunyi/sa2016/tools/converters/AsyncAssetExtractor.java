package com.beijunyi.sa2016.tools.converters;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;

public abstract class AsyncAssetExtractor<LA extends LegacyAsset, A extends GameAsset> implements AssetExtractor {

  private final LegacyAssetProvider<LA> provider;
  private final AssetExtractionTaskFactory<LA, A> tasks;

  protected AsyncAssetExtractor(LegacyAssetProvider<LA> provider, AssetExtractionTaskFactory<LA, A> tasks) {
    this.provider = provider;
    this.tasks = tasks;
  }

  @Nonnull
  public CompletableFuture<A> genAsset(int key) {
    return CompletableFuture.supplyAsync(tasks.newExtraction(key), ThreadPoolFactory.getInstance());
  }

  @Nonnull
  public CompletableFuture<List<A>> genAssets(List<Integer> keys) {
    return CompletableFuture.supplyAsync(
      () -> keys.stream()
              .map(this::genAsset)
              .map(CompletableFuture::join)
              .collect(Collectors.toList()),
      ThreadPoolFactory.getInstance()
    );
  }

  @Override
  public void extract() {
    provider.keys()
      .forEach(this::genAsset);
  }

}
