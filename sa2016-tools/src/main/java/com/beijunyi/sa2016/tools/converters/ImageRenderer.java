package com.beijunyi.sa2016.tools.converters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.PALET;
import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.lang.System.arraycopy;

@Singleton
public class ImageRenderer {

  private static final ImmutableList<Color> FIXED_HEAD
    = ImmutableList.of(
    new Color(0, 0, 0, 0),
    new Color(0, 4, 132, 255),
    new Color(0, 134, 0, 255),
    new Color(0, 134, 132, 255),
    new Color(132, 4, 0, 255),
    new Color(132, 4, 132, 255),
    new Color(132, 134, 0, 255),
    new Color(192, 192, 192, 255),
    new Color(198, 223, 198, 255),
    new Color(247, 207, 165, 255),
    new Color(0, 4, 222, 255),
    new Color(0, 93, 255, 255),
    new Color(165, 255, 255, 255),
    new Color(214, 93, 0, 255),
    new Color(255, 215, 82, 255),
    new Color(41, 231, 41, 255)
  );

  private static final ImmutableList<Color> FIXED_TAIL
    = ImmutableList.of(
    new Color(148, 199, 247, 255),
    new Color(90, 166, 231, 255),
    new Color(66, 125, 198, 255),
    new Color(24, 85, 156, 255),
    new Color(49, 69, 66, 255),
    new Color(24, 36, 41, 255),
    new Color(247, 255, 255, 255),
    new Color(165, 166, 165, 255),
    new Color(132, 134, 132, 255),
    new Color(0, 4, 255, 255),
    new Color(0, 255, 0, 255),
    new Color(0, 255, 255, 255),
    new Color(255, 4, 0, 255),
    new Color(255, 4, 255, 255),
    new Color(255, 255, 0, 255),
    new Color(255, 255, 255, 255)
  );

  private static final Kryo KRYO = KryoFactory.getInstance();
  private final ImmutableList<Color> colors;

  @Inject
  public ImageRenderer(ResourcesProvider resources) throws IOException {
    colors = readPalette(resources.getClientResource(PALET));
  }

  @Nonnull
  public RenderedImage render(ImageAsset image) {
    Real real = image.getReal();
    int width = real.getWidth();
    int height = real.getHeight();
    byte[] bitmap = new byte[width * height];
    readBitmap(real, bitmap);
    BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    for(int i = 0; i < width * height; i++) {
      int color = uint8(bitmap[i]);
      if(color != 0) ret.setRGB(i % width, i / width, colors.get(color).getRGB());
    }
    return ret;
  }

  private static void readBitmap(Real real, byte[] bitmap) {
    if(real.getMajor() == 1)
      readBitmap(real.getData(), bitmap);
    else
      arraycopy(real.getData(), 0, bitmap, 0, bitmap.length);
    verticalFlip(bitmap, real.getWidth(), real.getHeight());
  }

  private static void readBitmap(byte[] src, byte[] bitmap) {
    int length = src.length;
    int readPos = 0;
    int writePos = 0;
    while(readPos < length) {
      int head = uint8(src[readPos++]);
      byte value = 0;
      boolean copy;
      int x, y, z;
      if(head >= 224) {
        copy = false;
        value = 0;
        x = head - 224;
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 208) {
        copy = false;
        value = 0;
        x = 0;
        y = head - 208;
        z = uint8(src[readPos++]);
      } else if(head >= 192) {
        copy = false;
        value = 0;
        x = 0;
        y = 0;
        z = head - 192;
      } else if(head >= 160) {
        copy = false;
        value = src[readPos++];
        x = head - 160;
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 144) {
        copy = false;
        value = src[readPos++];
        x = 0;
        y = head - 144;
        z = uint8(src[readPos++]);
      } else if(head >= 128) {
        copy = false;
        value = src[readPos++];
        x = 0;
        y = 0;
        z = head - 128;
      } else if(head >= 32) {
        copy = true;
        x = head - 32;
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 16) {
        copy = true;
        x = 0;
        y = head - 16;
        z = uint8(src[readPos++]);
      } else {
        copy = true;
        x = 0;
        y = 0;
        z = head;
      }
      int total = x * 65536 + y * 256 + z;
      int canWrite = bitmap.length - writePos;
      if(total > canWrite) {
        total = canWrite;
      }
      if(copy) {
        int canRead = length - readPos;
        if(total > canRead) {
          total = canRead;
        }
        for(int i = 0; i < total; i++) {
          value = src[readPos++];
          bitmap[writePos++] = value;
        }
      } else {
        for(int i = 0; i < total; i++) {
          bitmap[writePos++] = value;
        }
      }
    }
  }

  private static void verticalFlip(byte[] data, int width, int height) {
    byte[] buf = new byte[width];
    for(int i = 0; i < height / 2; i++) {
      arraycopy(data, i * width, buf, 0, width);
      arraycopy(data, (height - i - 1) * width, data, i * width, width);
      arraycopy(buf, 0, data, (height - i - 1) * width, width);
    }
  }

  @Nonnull
  private static ImmutableList<Color> readPalette(Path file) throws IOException {
    Palet palet;
    try(Input input = new Input(Files.newInputStream(file))) {
      palet = KRYO.readObject(input, Palet.class);
    }
    return readPalette(palet);
  }

  @Nonnull
  private static ImmutableList<Color> readPalette(Palet palet) {
    ImmutableList.Builder<Color> builder = ImmutableList.builder();
    builder.addAll(FIXED_HEAD);
    for(PaletColor pColor : palet.getColors())
      builder.add(toColor(pColor));
    builder.addAll(FIXED_TAIL);
    return builder.build();
  }

  @Nonnull
  private static Color toColor(PaletColor pColor) {
    return new Color(pColor.getRed(), pColor.getGreen(), pColor.getBlue(), 255);
  }

}
