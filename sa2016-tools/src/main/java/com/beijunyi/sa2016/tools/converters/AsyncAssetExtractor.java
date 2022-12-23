package com.beijunyi.sa2016.tools.converters;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAsset;
import com.beijunyi.sa2016.tools.legacy.providers.LegacyAssetProvider;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AsyncAssetExtractor<LA extends LegacyAsset, A extends GameAsset>
    implements AssetExtractor {

  private static final Logger LOG = getLogger(AsyncAssetExtractor.class);

  private final LegacyAssetProvider<LA> provider;
  private final AssetExtractionTaskFactory<LA, A> tasks;

  protected AsyncAssetExtractor(
      LegacyAssetProvider<LA> provider, AssetExtractionTaskFactory<LA, A> tasks) {
    this.provider = provider;
    this.tasks = tasks;
  }

  public CompletableFuture<ImmutableList<A>> genAsset(int key) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return tasks.newExtraction(key).extract();
          } catch (IOException e) {
            LOG.error("Failed to extract {}.", key, e);
            throw new RuntimeException(e);
          }
        },
        ThreadPoolFactory.getInstance());
  }

  public CompletableFuture<ImmutableList<ImmutableList<A>>> genAssets(List<Integer> keys) {
    return CompletableFuture.supplyAsync(
        () ->
            keys.stream()
                .map(this::genAsset)
                .map(CompletableFuture::join)
                .collect(toImmutableList()),
        ThreadPoolFactory.getInstance());
  }

  @Override
  public void extract() {
    provider.keys().forEach(this::genAsset);
  }
}
