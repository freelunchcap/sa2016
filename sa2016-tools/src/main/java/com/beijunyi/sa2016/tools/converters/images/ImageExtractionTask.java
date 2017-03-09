package com.beijunyi.sa2016.tools.converters.images;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.graphics.BitmapRenderer;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import static java.nio.file.StandardOpenOption.READ;

class ImageExtractionTask implements Callable<Sprite> {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Adrn entry;
  private final Path archive;
  private final BitmapRenderer bitmapRenderer;
  private final SpriteRepo repo;

  ImageExtractionTask(Path archive, Adrn entry, BitmapRenderer bitmapRenderer, SpriteRepo repo) {
    this.archive = archive;
    this.entry = entry;
    this.bitmapRenderer = bitmapRenderer;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public Sprite call() {
    Sprite sprite = repo.get(entry.getUid());
    if(sprite == null) {
      ImageAsset legacy = readAsset();
      Texture texture = bitmapRenderer.createTexture(legacy);
      Adrn index = legacy.getIndex();
      sprite = new Sprite(legacy.getId(), texture.getId(), index.getWidth(), index.getHeight(), index.getXOffset(), index.getYOffset());
    }
    return sprite;
  }

  @Nonnull
  private ImageAsset readAsset() {
    try(SeekableByteChannel channel = Files.newByteChannel(archive, READ)) {
      channel.position(entry.getAddress());
      Input input = new Input(Channels.newInputStream(channel));
      return new ImageAsset(entry, KRYO.readObject(input, Real.class));
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }


}
