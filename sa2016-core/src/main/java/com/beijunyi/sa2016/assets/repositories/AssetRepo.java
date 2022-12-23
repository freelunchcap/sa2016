package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AssetRepo<A extends GameAsset> {

  private static final HashFunction SHA256 = Hashing.sha256();
  private static final BaseEncoding BASE32 = BaseEncoding.base32().lowerCase();

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Path dir;

  AssetRepo(CacheProvider cache) {
    this.dir = cache.root().resolve(namespace());
  }

  public void put(A asset) throws IOException {
    int id = asset.getId();
    Path assetDir = dir.resolve(Integer.toString(id));
    Files.createDirectories(assetDir);

    byte[] data = serialize(asset);
    HashCode hashCode = SHA256.hashBytes(data);
    String name = BASE32.encode(hashCode.asBytes());

    Path file = assetDir.resolve(name);
    Files.write(file, data);
  }

  public ImmutableList<A> get(int id) throws IOException {
    Path assetDir = dir.resolve(Integer.toString(id));
    if (!Files.isDirectory(assetDir)) {
      return ImmutableList.of();
    }

    ImmutableList.Builder<A> result = ImmutableList.builder();
    try (DirectoryStream<Path> assets = Files.newDirectoryStream(assetDir)) {
      for (Path asset : assets) {
        byte[] data = Files.readAllBytes(asset);
        A deserialized = deserialize(data);
        result.add(deserialized);
      }
    }

    return result.build();
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract Class<A> type();

  protected abstract byte[] serialize(A asset);

  protected abstract A deserialize(byte[] data);
}
