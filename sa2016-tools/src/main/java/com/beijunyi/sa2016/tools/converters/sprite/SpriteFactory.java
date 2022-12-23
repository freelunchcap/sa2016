package com.beijunyi.sa2016.tools.converters.sprite;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.converters.AssetFactory;
import com.beijunyi.sa2016.tools.converters.sprite.utils.Palette;
import com.beijunyi.sa2016.tools.converters.utils.MediaUtils;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteData;
import com.beijunyi.sa2016.tools.legacy.LegacySpriteHeader;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySprite;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.beijunyi.sa2016.tools.converters.sprite.utils.RunLengthDecoder.decode;
import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.System.arraycopy;

@Singleton
public class SpriteFactory implements AssetFactory<LegacySprite, Sprite> {

  private final Provider<Palette> palette;

  @Inject
  public SpriteFactory(Provider<Palette> palette) {
    this.palette = palette;
  }

  @Nonnull
  @Override
  public Sprite create(LegacySprite asset) throws IOException {
    LegacySpriteHeader header = asset.getHeader();
    return create(asset.getId(), header.xOffset(), header.yOffset(), asset.readData());
  }

  @Nonnull
  private Sprite create(int id, int xOffset, int yOffset, LegacySpriteData data) {
    int width = data.width();
    int height = data.height();
    byte[] bitmap = new byte[width * height];
    readBitmap(data, bitmap);
    BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
    for (int i = 0; i < width * height; i++) {
      int color = uint8(bitmap[i]);
      if (color != 0)
        image.setRGB(i % width, height - 1 - i / width, palette.get().getColor(color).getRGB());
    }
    return create(id, xOffset, yOffset, image);
  }

  @Nonnull
  private static Sprite create(int id, int xOffset, int yOffset, BufferedImage image) {
    Media media = MediaUtils.create(image);
    return new Sprite(id, image.getWidth(), image.getHeight(), xOffset, yOffset, media);
  }

  private static void readBitmap(LegacySpriteData legacySpriteData, byte[] bitmap) {
    if (legacySpriteData.major() == 1) {
      decode(legacySpriteData.data(), bitmap);
    } else {
      arraycopy(legacySpriteData.data(), 0, bitmap, 0, bitmap.length);
    }
  }
}
