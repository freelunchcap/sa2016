package com.beijunyi.sa2016.extraction.resources.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.context.EnvironmentContext;
import com.beijunyi.sa2016.extraction.context.EnvironmentService;

public class LegacyResourceLoader {

  private final EnvironmentService environmentService;

  @Inject
  public LegacyResourceLoader(@Nonnull EnvironmentService environmentService) {
    this.environmentService = environmentService;
  }

  @Nonnull
  public Set<Path> load(@Nonnull LegacyResource type) throws IOException {
    Path base = getResourceBase(type);
    Set<Path> ret = new HashSet<>();
    findResources(base.resolve(type.getRelativePath()), type, ret);
    return ret;
  }

  @Nonnull
  private Path getResourceBase(@Nonnull LegacyResource type) {
    Path ret;
    EnvironmentContext context = environmentService.getContext();
    switch(type.getBase()) {
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
    return ret;
  }

  private static void findResources(@Nonnull Path dir, @Nonnull LegacyResource type, @Nonnull Set<Path> results) throws IOException {
    if(Files.isDirectory(dir))
      return;
    try(DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
      findResources(files, type, results);
    }
  }

  private static void findResources(@Nonnull Iterable<Path> files, @Nonnull LegacyResource type, @Nonnull Set<Path> results) throws IOException {
    for(Path file : files) {
      if(Files.isDirectory(file)) {
        if(type.isRecursive())
          findResources(file, type, results);
      } else {
        if(matchesPattern(file, type) && matchesHeader(file, type))
          results.add(file);
      }
    }
  }

  private static boolean matchesPattern(@Nonnull Path file, @Nonnull LegacyResource type) {
    Pattern pattern = type.getPattern();
    if(pattern == null)
      return true;
    String filename = file.getFileName().toString();
    return pattern.matcher(filename).matches();
  }

  private static boolean matchesHeader(@Nonnull Path file, @Nonnull LegacyResource type) throws IOException {
    byte[] expected = type.getHeader();
    if(expected == null)
      return true;
    try(InputStream input = Files.newInputStream(file)) {
      byte[] actual = new byte[expected.length];
      return input.read(actual) == expected.length && Arrays.equals(expected, actual);
    }
  }

}
