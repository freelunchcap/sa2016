package com.beijunyi.sa2016.tools.resources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ResourceSignature {

  private final Pattern pattern;
  private final byte[] header;

  public ResourceSignature(@Nullable Pattern pattern, @Nullable byte[] header) {
    this.pattern = pattern;
    this.header = header;
  }

  @Nonnull
  public static ResourceSignature regexPattern(@Nonnull String regex) {
    return new ResourceSignature(Pattern.compile(regex), null);
  }

  @Nonnull
  public static ResourceSignature stringHeader(@Nonnull String header) {
    return new ResourceSignature(null, header.getBytes());
  }

  @Nonnull
  public static ResourceSignature all() {
    return new ResourceSignature(null, null);
  }

  public boolean matches(@Nonnull Path file) throws IOException {
    return matchesPattern(file) && matchesHeader(file);
  }

  private boolean matchesPattern(@Nonnull Path file) {
    if(pattern == null)
      return true;
    String filename = file.getFileName().toString();
    return pattern.matcher(filename).matches();
  }

  private boolean matchesHeader(@Nonnull Path file) throws IOException {
    if(header == null)
      return true;
    try(InputStream input = Files.newInputStream(file)) {
      byte[] actual = new byte[header.length];
      return input.read(actual) == header.length && Arrays.equals(header, actual);
    }
  }

}
