package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AssetRepo<A extends Asset> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  private final Path dir;

  AssetRepo(CacheProvider cache) {
    this.dir = cache.root().resolve(namespace());
  }

  public void put(A asset) throws IOException {
    int id = asset.getId();
    Path assetDir = dir.resolve(Integer.toString(id));
    Files.createDirectories(assetDir);
    serialize(asset, assetDir);
  }

  @Nullable
  public A get(int id) throws IOException {
    Path assetDir = dir.resolve(Integer.toString(id));
    if (!Files.isDirectory(assetDir)) {
      return null;
    }

    return deserialize(assetDir);
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract Class<A> type();

  protected abstract void serialize(A asset, Path dir);

  protected abstract A deserialize(Path dir);
}
