package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.cmd.EnvironmentContext;
import com.beijunyi.sa2016.tools.resources.ResourceSignature;

public class LegacyResourceFinder {

  private final EnvironmentContext context;

  @Inject
  public LegacyResourceFinder(@Nonnull EnvironmentContext context) {
    this.context = context;
  }

  @Nonnull
  public Set<Path> find(@Nonnull LegacyResource type) throws IOException {
    LegacyResourceLocation location = type.getLocation();
    Path dir = resolveLocation(location);
    Set<Path> ret = new HashSet<>();
    findResources(dir, location.isNested(), type.getSignature(), ret);
    return ret;
  }

  @Nonnull
  public Path findUnique(@Nonnull LegacyResource type) throws IOException {
    Set<Path> resources = find(type);
    if(resources.size() != 1)
      throw new IllegalStateException();
    return resources.iterator().next();
  }

  @Nonnull
  private Path resolveLocation(@Nonnull LegacyResourceLocation location) {
    Path ret;
    switch(location.getBase()) {
      case SERVER:
        ret = context.getServer();
        break;
      case CLIENT:
        ret = context.getClient();
        break;
      default:
        throw new UnsupportedOperationException();
    }
    if(ret == null)
      throw new IllegalStateException();
    return ret.resolve(location.getPath());
  }

  private static void findResources(@Nonnull Path dir, boolean recursive, @Nonnull ResourceSignature signature, @Nonnull Set<Path> results) throws IOException {
    if(!Files.isDirectory(dir))
      return;
    try(DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
      findResources(files, recursive, signature, results);
    }
  }

  private static void findResources(@Nonnull Iterable<Path> files, boolean recursive, @Nonnull ResourceSignature signature, @Nonnull Set<Path> results) throws IOException {
    for(Path file : files) {
      if(Files.isDirectory(file)) {
        if(recursive)
          findResources(file, true, signature, results);
      } else {
        if(signature.matches(file))
          results.add(file);
      }
    }
  }

}
