package com.beijunyi.sa2016.tools.converters;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.beijunyi.sa2016.tools.ToolsContext.IMAGE_FORMAT;

@Singleton
public class ImageExtractor implements AssetExtractor {

  private static final Logger LOG = LoggerFactory.getLogger(ImageExtractor.class);

  private final ImageLocator locator;
  private final ImageRenderer renderer;
  private final ImageRepo repo;

  @Inject
  public ImageExtractor(ImageLocator locator, ImageRenderer renderer, ImageRepo repo) {
    this.locator = locator;
    this.renderer = renderer;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public String name() {
    return "images";
  }

  @Override
  public void extract() {
    Iterator<ImageAsset> assets = locator.imageAssets();
    while(assets.hasNext()) {
      ImageAsset next = assets.next();
      try {
        RenderedImage image = renderer.render(next);
        saveImage(next, image);
      } catch(Exception e) {
        LOG.warn("Could not render {}", next.getId(), e);
      }
    }
  }

  private void saveImage(ImageAsset raw, RenderedImage rendered) {
    try(ByteOutputStream stream = new ByteOutputStream()) {
      ImageIO.write(rendered, IMAGE_FORMAT, stream);
      Adrn meta = raw.getAdrn();
      repo.put(new Image(raw.getId(), IMAGE_FORMAT, meta.getWidth(), meta.getHeight(), meta.getXOffset(), meta.getYOffset(), stream.getBytes()));
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }


}
