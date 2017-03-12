package com.beijunyi.sa2016.tools.converters.sprite;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.converters.graphics.Palette;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySprite;

import static com.beijunyi.sa2016.tools.ToolsVariables.IMAGE_FORMAT;
import static com.beijunyi.sa2016.tools.converters.graphics.RunLengthDecoder.decodeBitmap;
import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.System.arraycopy;

@Singleton
public class SpriteFactory {

  private final Palette palette;

  @Inject
  public SpriteFactory(Palette palette) {
    this.palette = palette;
  }

  @Nonnull
  Sprite create(LegacySprite asset) throws IOException {
    LegacySpriteHeader header = asset.getHeader();
    return create(asset.getId(), header.getXOffset(), header.getYOffset(), asset.readData());
  }

  @Nonnull
  private Sprite create(int id, int xOffset, int yOffset, LegacySpriteData data) throws IOException {
    int width = data.getWidth();
    int height = data.getHeight();
    byte[] bitmap = new byte[width * height];
    readBitmap(data, bitmap);
    BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
    for(int i = 0; i < width * height; i++) {
      int color = uint8(bitmap[i]);
      if(color != 0) image.setRGB(i % width, height - 1 - i / width, palette.getColor(color).getRGB());
    }
    return create(id, xOffset, yOffset, image);
  }

  @Nonnull
  private static Sprite create(int id, int xOffset, int yOffset, BufferedImage image) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(image, IMAGE_FORMAT, out);
    Media media = new Media(IMAGE_FORMAT, out.toByteArray());
    return new Sprite(id, image.getWidth(), image.getHeight(), xOffset, yOffset, media);
  }

  private static void readBitmap(LegacySpriteData legacySpriteData, byte[] bitmap) {
    if(legacySpriteData.getMajor() == 1) {
      decodeBitmap(legacySpriteData.getData(), bitmap);
    } else {
      arraycopy(legacySpriteData.getData(), 0, bitmap, 0, bitmap.length);
    }
  }

}
