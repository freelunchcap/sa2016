package com.beijunyi.sa2016.tools.converters.images;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;

import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.ToolsContext.IMAGE_FORMAT;
import static java.nio.file.StandardOpenOption.READ;

class ImageExtractionTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(ImageExtractionTask.class);
  private static final Kryo KRYO = KryoFactory.getInstance();

  private final Adrn entry;
  private final Path archive;
  private final ImageRenderer renderer;
  private final ImageRepo repo;

  ImageExtractionTask(Path archive, Adrn entry, ImageRenderer renderer, ImageRepo repo) {
    this.archive = archive;
    this.entry = entry;
    this.renderer = renderer;
    this.repo = repo;
  }

  @Override
  public void run() {
    ImageAsset legacy = readAsset();
    try {
      RenderedImage image = renderer.render(legacy);
      saveImage(legacy, image);
    } catch(Exception e) {
      LOG.warn("Could not render {}", legacy.getId(), e);
    }
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

  private void saveImage(ImageAsset raw, RenderedImage rendered) {
    try(ByteOutputStream stream = new ByteOutputStream()) {
      ImageIO.write(rendered, IMAGE_FORMAT, stream);
      Adrn meta = raw.getIndex();
      repo.put(new Image(raw.getId(), IMAGE_FORMAT, meta.getWidth(), meta.getHeight(), meta.getXOffset(), meta.getYOffset(), stream.getBytes()));
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
