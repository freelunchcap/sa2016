package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.Asset;
import com.beijunyi.sa2016.assets.serializers.AssetSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ThreadSafe
public abstract class AssetRepo<A extends Asset> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  private final Path dir;
  private final AssetSerializer<A> serializer;

  AssetRepo(CacheProvider cache, AssetSerializer<A> serializer) {
    this.dir = cache.root().resolve(namespace());
    this.serializer = serializer;
  }

  public void put(A asset) throws IOException {
    int id = asset.getId();
    Path assetDir = dir.resolve(Integer.toString(id));
    Files.createDirectories(assetDir);
    serializer.serialize(asset, assetDir);
  }

  @Nullable
  public A get(int id) throws IOException {
    Path assetDir = dir.resolve(Integer.toString(id));
    if (!Files.isDirectory(assetDir)) {
      return null;
    }

    return serializer.deserialize(id, assetDir);
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract Class<A> type();
}
