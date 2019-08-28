package com.beijunyi.sa2016.assets.repositories;

import static com.beijunyi.sa2016.AppConstants.APP_HOME;
import static com.google.common.collect.ImmutableList.toImmutableList;

import com.beijunyi.sa2016.assets.Asset;
import com.beijunyi.sa2016.assets.serializers.AssetSerializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AssetRepo<A extends Asset> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  AssetRepo() {
    try {
      Files.createDirectories(getDirectory());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void put(A asset) {
    try (OutputStream out = Files.newOutputStream(getFilePath(asset.getId()))) {
      serializer().write(asset, out);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  public ImmutableList<A> list(
      @Nullable String start, @Nullable String dir, @Nullable Integer max) {
    start = start == null ? "" : start;
    dir = dir == null ? "gte" : dir;
    max = max == null ? 10 : max;
    try {
      return Files.list(getDirectory())
          .sorted(order(dir))
          .filter(filter(start, dir))
          .limit(max)
          .map(this::get)
          .collect(toImmutableList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Nullable
  public A get(String id) {
    return get(getFilePath(id));
  }

  @Nullable
  private A get(Path path) {
    try (InputStream in = Files.newInputStream(path)) {
      return serializer().read(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract AssetSerializer<A> serializer();

  private Path getDirectory() {
    return APP_HOME.resolve(namespace());
  }

  private Path getFilePath(String id) {
    return getDirectory().resolve(id + serializer().suffix());
  }

  private Comparator<Path> order(String dir) {
    if (dir.startsWith("lt")) {
      return Ordering.natural().reverse();
    }
    return Ordering.natural();
  }

  private Predicate<Path> filter(String start, String dir) {
    return file -> {
      String filename = file.getFileName().toString();
      String first = start + serializer().suffix();
      switch (dir) {
        case "gte":
          return filename.compareTo(first) >= 0;
        case "gt":
          return filename.compareTo(first) > 0;
        case "lte":
          return filename.compareTo(first) <= 0;
        case "lt":
          return filename.compareTo(first) < 0;
        case "e":
          return filename.compareTo(first) == 0;
      }
      throw new IllegalArgumentException(dir);
    };
  }
}
