package com.beijunyi.sa2016.tools.legacy;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static java.nio.file.StandardOpenOption.READ;

public abstract class LegacyDataSource<T> {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Path file;
  private final long position;

  public LegacyDataSource(Path file, long position) {
    this.file = file;
    this.position = position;
  }

  @Nonnull
  protected abstract T read(Kryo kryo, Input input);

  @Nonnull
  public T read() throws IOException {
    try(SeekableByteChannel channel = Files.newByteChannel(file, READ)) {
      channel.position(position);
      Input input = new Input(Channels.newInputStream(channel));
      return read(KRYO, input);
    }
  }

}
