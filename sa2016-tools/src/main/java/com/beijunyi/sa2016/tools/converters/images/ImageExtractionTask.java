package com.beijunyi.sa2016.tools.converters.images;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.Texture;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.beijunyi.sa2016.tools.converters.textures.TextureFactory;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import static java.nio.file.StandardOpenOption.READ;

class ImageExtractionTask implements Callable<Image> {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Adrn entry;
  private final Path archive;
  private final TextureFactory textureFactory;
  private final ImageRepo repo;

  ImageExtractionTask(Path archive, Adrn entry, TextureFactory textureFactory, ImageRepo repo) {
    this.archive = archive;
    this.entry = entry;
    this.textureFactory = textureFactory;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public Image call() {
    Image image = repo.get(entry.getUid());
    if(image == null) {
      ImageAsset legacy = readAsset();
      Texture texture = textureFactory.createTexture(legacy);
      Adrn index = legacy.getIndex();
      image = new Image(legacy.getId(), texture.getId(), index.getWidth(), index.getHeight(), index.getXOffset(), index.getYOffset());
    }
    return image;
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
