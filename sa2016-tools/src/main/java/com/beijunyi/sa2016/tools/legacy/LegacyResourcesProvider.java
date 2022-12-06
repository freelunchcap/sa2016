package com.beijunyi.sa2016.tools.legacy;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static com.google.common.collect.Iterables.getOnlyElement;

@Singleton
public final class LegacyResourcesProvider {

  private final ImmutableSetMultimap<LegacyResourceType, Path> resources = indexResources();

  @Nonnull
  public ImmutableSet<Path> getResourceFiles(LegacyResourceType type) {
    return resources.get(type);
  }

  @Nonnull
  public Path getResourceFile(LegacyResourceType type) {
    return getOnlyElement(getResourceFiles(type));
  }

  @Nonnull
  private static ImmutableSetMultimap<LegacyResourceType, Path> indexResources() {
    ImmutableSetMultimap.Builder<LegacyResourceType, Path> ret = ImmutableSetMultimap.builder();
    for (LegacyResourceType resource : LegacyResourceType.values()) {
      LegacyResourceLocation type = resource.type();
      ret.putAll(resource, findResources(type.getLocation(), resource));
    }
    return ret.build();
  }

  @Nonnull
  private static ImmutableSet<Path> findResources(Path root, LegacyResourceType type) {
    Path start = root.resolve(type.path());
    ImmutableSet.Builder<Path> ret = ImmutableSet.builder();
    if (Files.exists(root)) {
      try (Stream<Path> stream = type.recursive() ? Files.walk(start) : Files.list(start)) {
        stream.filter(type::matches).forEach(ret::add);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    return ret.build();
  }
}
