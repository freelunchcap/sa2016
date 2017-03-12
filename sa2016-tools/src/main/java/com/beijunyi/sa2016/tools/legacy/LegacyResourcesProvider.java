package com.beijunyi.sa2016.tools.legacy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.inject.Singleton;

import com.google.common.collect.*;

import static com.google.common.collect.Iterables.getOnlyElement;

@Singleton
public class LegacyResourcesProvider {

  private final Multimap<LegacyResourceType, Path> resources = indexResources();

  @Nonnull
  public Collection<Path> getResourceFiles(LegacyResourceType type) {
    return resources.get(type);
  }

  @Nonnull
  public Path getResourceFile(LegacyResourceType type) {
    return getOnlyElement(getResourceFiles(type));
  }

  @Nonnull
  private static Multimap<LegacyResourceType, Path> indexResources() {
    ImmutableMultimap.Builder<LegacyResourceType, Path> ret = ImmutableMultimap.builder();
    for(LegacyResourceType resource : LegacyResourceType.values()) {
      LegacyResourceLocation type = resource.type();
      ret.putAll(resource, findResources(type.getLocation(), resource));
    }
    return ret.build();
  }

  @Nonnull
  private static Collection<Path> findResources(Path root, LegacyResourceType type) {
    Path start = root.resolve(type.path());
    ImmutableCollection.Builder<Path> ret = ImmutableList.builder();
    if(Files.exists(root)) {
      try {
        Stream<Path> stream = type.recursive() ? Files.walk(start) : Files.list(start);
        stream.filter(type::matches).forEach(ret::add);
      } catch(IOException e) {
        throw new IllegalStateException(e);
      }
    }
    return ret.build();
  }

}
