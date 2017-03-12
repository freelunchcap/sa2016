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

  private final Multimap<LegacyResource, Path> resources = indexResources();

  @Nonnull
  public Collection<Path> getResourceFiles(LegacyResource resource) {
    return resources.get(resource);
  }

  @Nonnull
  public Path getResourceFile(LegacyResource resource) {
    return getOnlyElement(getResourceFiles(resource));
  }

  @Nonnull
  private static Multimap<LegacyResource, Path> indexResources() {
    ImmutableMultimap.Builder<LegacyResource, Path> ret = ImmutableMultimap.builder();
    for(LegacyResource resource : LegacyResource.values()) {
      LegacyResourceType type = resource.type();
      ret.putAll(resource, findResources(type.getLocation(), resource));
    }
    return ret.build();
  }

  @Nonnull
  private static Collection<Path> findResources(Path root, LegacyResource resource) {
    Path start = root.resolve(resource.path());
    ImmutableCollection.Builder<Path> ret = ImmutableList.builder();
    try {
      Stream<Path> stream = resource.recursive() ? Files.walk(start) : Files.list(start);
      stream.filter(resource::matches).forEach(ret::add);
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
    return ret.build();
  }

}
