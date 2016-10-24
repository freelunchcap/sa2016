package com.beijunyi.sa2016.tools.legacy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.google.common.collect.*;

import static com.beijunyi.sa2016.tools.ToolsContext.CLIENT_RESOURCES;
import static com.beijunyi.sa2016.tools.ToolsContext.SERVER_RESOURCES;
import static com.google.common.collect.Iterables.getOnlyElement;

public class ResourcesProvider {

  private final ImmutableMultimap<ClientResource, Path> client = indexResources(CLIENT_RESOURCES, ClientResource.values());
  private final ImmutableMultimap<ServerResource, Path> server = indexResources(SERVER_RESOURCES, ServerResource.values());

  @Nonnull
  public ImmutableCollection<Path> getClientResources(ClientResource type) {
    return client.get(type);
  }

  @Nonnull
  public Path getClientResource(ClientResource type) {
    return getOnlyElement(getClientResources(type));
  }

  @Nonnull
  public ImmutableCollection<Path> getServerResources(ServerResource type) {
    return server.get(type);
  }

  @Nonnull
  public Path getServerResource(ServerResource type) {
    return getOnlyElement(getServerResources(type));
  }

  @Nonnull
  private static <R extends LegacyResource> ImmutableMultimap<R, Path> indexResources(Path root, R[] types) {
    ImmutableMultimap.Builder<R, Path> ret = ImmutableMultimap.builder();
    for(R type : types) ret.putAll(type, findResources(root, type));
    return ret.build();
  }

  @Nonnull
  private static <R extends LegacyResource> ImmutableCollection<Path> findResources(Path root, R type) {
    Path start = root.resolve(type.path());
    ImmutableCollection.Builder<Path> ret = ImmutableList.builder();
    try {
      Stream<Path> stream = type.recursive() ? Files.walk(start) : Files.list(start);
      stream.filter(type::matches).forEach(ret::add);
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
    return ret.build();
  }

}
