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

import static java.nio.file.StandardOpenOption.READ;

interface LegacyResource {

  Logger LOG = LoggerFactory.getLogger(LegacyResource.class);

  @Nonnull
  String path();

  @Nullable
  PathMatcher pattern();

  @Nullable
  byte[] header();

  boolean recursive();

  default boolean matches(Path path) {
    return filenameMatches(path) && headerMatches(path);
  }

  default boolean filenameMatches(Path path) {
    PathMatcher expect = pattern();
    return expect == null || expect.matches(path);
  }

  default boolean headerMatches(Path path) {
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

}
