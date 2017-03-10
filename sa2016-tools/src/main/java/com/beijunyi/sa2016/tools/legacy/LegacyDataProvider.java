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

public abstract class LegacyDataProvider<T> {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Path archive;
  private final long position;

  public LegacyDataProvider(Path archive, long position) {
    this.archive = archive;
    this.position = position;
  }

  @Nonnull
  protected abstract Class<T> getDataType();

  @Nonnull
  public T read() throws IOException {
    try(SeekableByteChannel channel = Files.newByteChannel(archive, READ)) {
      channel.position(position);
      Input input = new Input(Channels.newInputStream(channel));
      return KRYO.readObject(input, getDataType());
    }
  }

}
