package com.beijunyi.sa2016.tools.resources.transform.palette;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.model.Bitmap;
import com.beijunyi.sa2016.tools.utils.BitConverter;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Palette {

  private final List<Color> colors;

  public Palette(@Nonnull List<Color> colors) {
    this.colors = colors;
  }

  @Nonnull
  public RenderedImage render(@Nonnull Bitmap bitmap) {
    BufferedImage ret = new BufferedImage(bitmap.getWidth(), bitmap.getHeight(), TYPE_INT_ARGB);
    byte[] pixels = bitmap.getPixels();
    int x = 0, y = 0, pos = 0;
    while(pos < pixels.length) {
      int colorIdx = BitConverter.uint8(pixels[pos++]);
      if(colorIdx != 0)
        ret.setRGB(x, y, colors.get(colorIdx).getRGB());
      if(++x == bitmap.getWidth()) {
        x = 0;
        y++;
      }
    }
    return ret;
  }

}
