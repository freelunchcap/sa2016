package com.beijunyi.sa2016.tools.legacy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.ToolsVariables.*;
import static java.nio.file.StandardOpenOption.READ;

public enum LegacyResource {

  ADRN
    (
      LegacyResourceType.CLIENT,
      "data",
      matchPattern(CLIENT_ADRN_PATTERN)
    ),

  REAL
    (
      LegacyResourceType.CLIENT,
      "data",
      matchPattern(CLIENT_REAL_PATTERN)
    ),

  SPR
    (
      LegacyResourceType.CLIENT,
      "data",
      matchPattern(CLIENT_SPR_PATTERN)
    ),

  SPRADRN
    (
      LegacyResourceType.CLIENT,
      "data",
      matchPattern(CLIENT_SPRADRN_PATTERN)
    ),

  PALET
    (
      LegacyResourceType.CLIENT,
      "data/pal",
      matchPattern(CLIENT_PALET_PATTERN)
    );

  Logger LOG = LoggerFactory.getLogger(LegacyResource.class);

  private final LegacyResourceType type;
  private final String path;
  private final PathMatcher pattern;
  private final byte[] header;
  private final boolean recursive;

  LegacyResource(LegacyResourceType type, String path, PathMatcher pattern) {
    this(type, path, pattern, null, false);
  }

  LegacyResource(LegacyResourceType type, String path, PathMatcher pattern, @Nullable byte[] header, boolean recursive) {
    this.type = type;
    this.path = path;
    this.pattern = pattern;
    this.header = header;
    this.recursive = recursive;
  }

  @Nonnull
  public LegacyResourceType type() {
    return type;
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
    if(expect == null) return true;
    ByteBuffer actual = ByteBuffer.allocate(expect.length);
    try(SeekableByteChannel channel = Files.newByteChannel(path, READ)) {
      channel.read(actual);
    } catch(IOException e) {
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
