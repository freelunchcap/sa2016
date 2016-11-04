package com.beijunyi.sa2016.tools.converters.characters;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.repositories.CharacterRepo;
import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.nio.file.StandardOpenOption.READ;

class CharacterExtractionTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(CharacterExtractionTask.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final SprAdrn entry;
  private final Path archive;
  private final CharacterFactory factory;
  private final CharacterRepo repo;

  CharacterExtractionTask(Path archive, SprAdrn entry, CharacterFactory factory, CharacterRepo repo) {
    this.archive = archive;
    this.entry = entry;
    this.factory = factory;
    this.repo = repo;
  }

  @Override
  public void run() {
    try {
      CharacterAsset legacy = readAsset();
      Character character = factory.newCharacter(legacy);
      repo.put(character);
    } catch(Exception e) {
      LOG.warn("Could not extract character {}", entry.getId(), e);
    }
  }

  @Nonnull
  private CharacterAsset readAsset() throws IOException {
    ImmutableTable.Builder<Action, Direction, Spr> ret = ImmutableTable.builder();
    try(SeekableByteChannel channel = Files.newByteChannel(archive, READ)) {
      channel.position(entry.getAddress());
      Input input = new Input(Channels.newInputStream(channel));
      for(int a = 0; a < entry.getAnimations(); a++) {
        Spr spr = KRYO.readObject(input, Spr.class);
        Action action = Action.values()[spr.getAction()];
        Direction direction = Direction.values()[spr.getDirection()];
        ret.put(action, direction, spr);
      }
    }
    return new CharacterAsset(entry, ret.build());
  }


}
