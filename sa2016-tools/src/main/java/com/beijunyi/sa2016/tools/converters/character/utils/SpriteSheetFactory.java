package com.beijunyi.sa2016.tools.converters.character.utils;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.SpriteSheet;
import com.beijunyi.sa2016.tools.converters.sprite.AsyncSpriteExtractor;
import com.beijunyi.sa2016.tools.converters.utils.MediaUtils;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimationFrame;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.String.format;

@Singleton
public final class SpriteSheetFactory {

  private static final Logger LOG = LoggerFactory.getLogger(SpriteSheetFactory.class);

  private final AsyncSpriteExtractor extractor;

  @Inject
  SpriteSheetFactory(AsyncSpriteExtractor extractor) {
    this.extractor = extractor;
  }

  @Nonnull
  public SpriteSheet create(LegacyAnimation animation) {
    List<Integer> keys =
        animation.frames().stream().map(LegacyAnimationFrame::sprite).collect(Collectors.toList());
    List<ImmutableList<Sprite>> sprites = extractor.genAssets(keys).join();
    if (sprites.size() != keys.size()) {
      throw new IllegalStateException(
          format("Expected assets %d, actual assets %d", keys.size(), sprites.size()));
    }

    List<Sprite> flatten = new ArrayList<>();
    for (int i = 0; i < keys.size(); i++) {
      int id = keys.get(i);
      List<Sprite> assets = sprites.get(i);
      if (assets.isEmpty()) {
        LOG.error("Asset {} is missing.", id);
      } else {
        flatten.add(assets.get(0));
        if (assets.size() > 1) {
          LOG.warn("Duplicated asset {}.", id);
        }
      }
    }

    return combine(flatten);
  }

  @Nonnull
  private static SpriteSheet combine(List<Sprite> sprites) {
    int top = 0, left = 0, bottom = 0, right = 0;
    for (Sprite sprite : sprites) {
      top = Math.min(top, sprite.getYOffset());
      left = Math.min(left, sprite.getXOffset());
      bottom = Math.max(bottom, sprite.getYOffset() + sprite.getHeight());
      right = Math.max(right, sprite.getXOffset() + sprite.getWidth());
    }
    final int frameWidth = right - left, frameHeight = bottom - top;
    final int frames = sprites.size();
    final int xOffset = left, yOffset = top;
    BufferedImage sheet = new BufferedImage(frameWidth * frames, frameHeight, TYPE_INT_ARGB);
    for (int s = 0; s < sprites.size(); s++) {
      BufferedImage frame = readSpriteImage(sprites.get(s));
      copyImage(frame, sheet, s * frameWidth);
    }
    Media media = MediaUtils.create(sheet);
    return new SpriteSheet(frameWidth, frameHeight, frames, xOffset, yOffset, media);
  }

  @Nonnull
  private static BufferedImage readSpriteImage(Sprite sprite) {
    try {
      return ImageIO.read(new ByteArrayInputStream(sprite.getMedia().data()));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private static void copyImage(BufferedImage src, BufferedImage dest, int destXOffset) {
    int[] srcData = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
    int[] destData = ((DataBufferInt) dest.getRaster().getDataBuffer()).getData();
    int width = src.getWidth();
    int height = src.getHeight();
    int srcPos = 0;
    int destPos = destXOffset;
    for (int y = 0; y < height; y++, destPos += dest.getWidth(), srcPos += width) {
      System.arraycopy(srcData, srcPos, destData, destPos, width);
    }
  }
}
