package com.beijunyi.sa2016.tools.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;

import static com.beijunyi.sa2016.tools.ToolsVariables.*;
import static com.beijunyi.sa2016.tools.legacy.LegacyResourceLocation.CLIENT;
import static java.nio.file.StandardOpenOption.READ;

public enum LegacyResourceType {
  ADRN(CLIENT, "data", matchPattern(CLIENT_ADRN_PATTERN)),

  REAL(CLIENT, "data", matchPattern(CLIENT_REAL_PATTERN)),

  SPR(CLIENT, "data", matchPattern(CLIENT_SPR_PATTERN)),

  SPRADRN(CLIENT, "data", matchPattern(CLIENT_SPRADRN_PATTERN)),

  PALET(CLIENT, "data/pal", matchPattern(CLIENT_PALET_PATTERN));

  private static final Logger LOG = LoggerFactory.getLogger(LegacyResourceType.class);

  private final LegacyResourceLocation location;
  private final String path;
  private final PathMatcher pattern;
  private final byte[] header;
  private final boolean recursive;

  LegacyResourceType(LegacyResourceLocation location, String path, PathMatcher pattern) {
    this(location, path, pattern, null, false);
  }

  LegacyResourceType(
      LegacyResourceLocation location,
      String path,
      PathMatcher pattern,
      @Nullable byte[] header,
      boolean recursive) {
    this.location = location;
    this.path = path;
    this.pattern = pattern;
    this.header = header;
    this.recursive = recursive;
  }

  @Nonnull
  public LegacyResourceLocation type() {
    return location;
  }

  @Nonnull
  String path() {
    return path;
  }

  @Nullable
  PathMatcher pattern() {
    return pattern;
  }

  @Nullable
  byte[] header() {
    return header;
  }

  boolean recursive() {
    return recursive;
  }

  public boolean matches(Path path) {
    return filenameMatches(path) && headerMatches(path);
  }

  public boolean filenameMatches(Path path) {
    PathMatcher expect = pattern();
    return expect == null || expect.matches(path.getFileName());
  }

  public boolean headerMatches(Path path) {
    byte[] expect = header();
    if (expect == null) return true;
    ByteBuffer actual = ByteBuffer.allocate(expect.length);
    try (SeekableByteChannel channel = Files.newByteChannel(path, READ)) {
      channel.read(actual);
    } catch (IOException e) {
      LOG.error("Could not read file header {}", path, e);
      return false;
    }
    return Arrays.equals(expect, actual.array());
  }

  @Nonnull
  private static PathMatcher matchPattern(String path) {
    return FileSystems.getDefault().getPathMatcher("glob:" + path);
  }
}
