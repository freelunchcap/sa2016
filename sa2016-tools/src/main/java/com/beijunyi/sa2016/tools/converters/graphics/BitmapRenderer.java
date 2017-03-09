package com.beijunyi.sa2016.tools.converters.graphics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.sprite.SpriteAsset;
import com.beijunyi.sa2016.tools.legacy.*;

import static com.beijunyi.sa2016.tools.ToolsContext.IMAGE_FORMAT;
import static com.beijunyi.sa2016.tools.converters.graphics.RunLengthDecoder.decodeBitmap;
import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.System.arraycopy;

@Singleton
public class BitmapRenderer {

  private final Palette palette;

  @Inject
  public BitmapRenderer(Palette palette) throws IOException {
    this.palette = palette;
  }

  @Nonnull
  public Texture createTexture(SpriteAsset asset) {
    Texture texture = textureRepo.get(asset.getId());
    if(texture == null) {
      texture = renderTexture(asset);
    }
    return texture;
  }

  @Nonnull
  private Texture renderTexture(SpriteAsset asset) {
    Real real = asset.getData();
    int width = real.getWidth();
    int height = real.getHeight();
    byte[] bitmap = new byte[width * height];
    readBitmap(real, bitmap);
    BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
    for(int i = 0; i < width * height; i++) {
      int color = uint8(bitmap[i]);
      if(color != 0) image.setRGB(i % width, height - 1 - i / width, colors.get(color).getRGB());
    }
    return toTexture(asset.getId(), image);
  }

  @Nonnull
  private static Texture toTexture(int id, BufferedImage image) {
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ImageIO.write(image, IMAGE_FORMAT, out);
      return new Texture(id, IMAGE_FORMAT, out.toByteArray());
    } catch(IOException e) {
      throw new IllegalStateException();
    }
  }

  private static void readBitmap(Real real, byte[] bitmap) {
    if(real.getMajor() == 1) {
      decodeBitmap(real.getData(), bitmap);
    } else {
      arraycopy(real.getData(), 0, bitmap, 0, bitmap.length);
    }
  }

}
