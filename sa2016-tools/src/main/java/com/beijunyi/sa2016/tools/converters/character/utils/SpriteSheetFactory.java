package com.beijunyi.sa2016.tools.converters.character.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.SpriteSheet;
import com.beijunyi.sa2016.tools.converters.sprite.AsyncSpriteExtractor;
import com.beijunyi.sa2016.tools.converters.utils.MediaUtils;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimationFrame;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

@Singleton
public class SpriteSheetFactory {

  private final AsyncSpriteExtractor extractor;

  @Inject
  public SpriteSheetFactory(AsyncSpriteExtractor extractor) {
    this.extractor = extractor;
  }

  @Nonnull
  public SpriteSheet create(LegacyAnimation animation) {
    List<Integer> keys = animation.getFrames()
                              .stream()
                              .map(LegacyAnimationFrame::getSprite)
                              .collect(Collectors.toList());
    List<Sprite> sprites = extractor.genAssets(keys).join();
    return combine(sprites);
  }

  @Nonnull
  private static SpriteSheet combine(List<Sprite> sprites) {
    int top = 0, left = 0, bottom = 0, right = 0;
    for(Sprite sprite : sprites) {
      top = Math.min(top, sprite.getYOffset());
      left = Math.min(left, sprite.getXOffset());
      bottom = Math.max(bottom, sprite.getYOffset() + sprite.getHeight());
      right = Math.max(right, sprite.getXOffset() + sprite.getWidth());
    }
    final int frameWidth = right - left, frameHeight = bottom - top;
    final int frames = sprites.size();
    final int xOffset = left, yOffset = top;
    BufferedImage sheet = new BufferedImage(frameWidth * frames, frameHeight, TYPE_INT_ARGB);
    for(int s = 0; s < sprites.size(); s++) {
      BufferedImage frame = readSpriteImage(sprites.get(s));
      copyImage(frame, sheet, s * frameWidth);
    }
    Media media = MediaUtils.create(sheet);
    return new SpriteSheet(frameWidth, frameHeight, frames, xOffset, yOffset, media);
  }

  @Nonnull
  private static BufferedImage readSpriteImage(Sprite sprite) {
    try {
      return ImageIO.read(new ByteArrayInputStream(sprite.getMedia().getData()));
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private static void copyImage(BufferedImage src, BufferedImage dest, int destXOffset) {
    int[] srcData = ((DataBufferInt)src.getRaster().getDataBuffer()).getData();
    int[] destData = ((DataBufferInt)dest.getRaster().getDataBuffer()).getData();
    int width = src.getWidth();
    int height = src.getHeight();
    int srcPos = 0;
    int destPos = destXOffset;
    for(int y = 0; y < height; y++, destPos += dest.getWidth(), srcPos += width) {
      System.arraycopy(srcData, srcPos, destData, destPos, width);
    }
  }

}
