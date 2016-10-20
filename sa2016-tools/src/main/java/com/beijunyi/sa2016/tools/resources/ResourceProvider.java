package com.beijunyi.sa2016.tools.resources;

import java.nio.file.Path;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.ClientResource;
import com.beijunyi.sa2016.tools.legacy.ServerResource;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Iterables;

public class ResourceProvider {

  private ImmutableMultimap<ClientResource, Path> clientResources;
  private ImmutableMultimap<ServerResource, Path> serverResources;

  @Nonnull
  public ImmutableCollection<Path> getClientResources(ClientResource type) {
    return clientResources.get(type);
  }

  @Nonnull
  public Path getClientResource(ClientResource type) {
    return Iterables.getOnlyElement(getClientResources(type));
  }



}
