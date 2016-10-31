package com.beijunyi.sa2016.tools.converters.characters;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.repositories.CharacterRepo;
import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static java.nio.file.StandardOpenOption.READ;

class CharacterExtractionTask implements Runnable {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final SprAdrn entry;
  private final Path archive;
  private final CharacterFactory characters;
  private final CharacterRepo repo;

  CharacterExtractionTask(Path archive, SprAdrn entry, CharacterFactory characters, CharacterRepo repo) {
    this.archive = archive;
    this.entry = entry;
    this.characters = characters;
    this.repo = repo;
  }

  @Override
  public void run() {
    Spr legacy = readAsset();
    Character character = characters.newCharacter(legacy);
    repo.put(character);
  }

  @Nonnull
  private Spr readAsset() {
    try(SeekableByteChannel channel = Files.newByteChannel(archive, READ)) {
      channel.position(entry.getAddress());
      Input input = new Input(Channels.newInputStream(channel));
      return KRYO.readObject(input, Spr.class);
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }


}
