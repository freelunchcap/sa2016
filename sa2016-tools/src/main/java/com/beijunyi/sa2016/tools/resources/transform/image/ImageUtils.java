package com.beijunyi.sa2016.tools.resources.transform.image;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;

public class ImageUtils {

  public static byte[] PALETTE_BEGIN =
    new byte[] {
                 (byte)   0, (byte)   0, (byte)   0, (byte) 0,
                 (byte) 132, (byte)   4, (byte)   0, (byte) 255,
                 (byte)   0, (byte) 134, (byte)   0, (byte) 255,
                 (byte) 132, (byte) 134, (byte)   0, (byte) 255,
                 (byte)   0, (byte)   4, (byte) 132, (byte) 255,
                 (byte) 132, (byte)   4, (byte) 132, (byte) 255,
                 (byte)   0, (byte) 134, (byte) 132, (byte) 255,
                 (byte) 192, (byte) 192, (byte) 192, (byte) 255,
                 (byte) 198, (byte) 223, (byte) 198, (byte) 255,
                 (byte) 165, (byte) 207, (byte) 247, (byte) 255,
                 (byte) 222, (byte)   4, (byte)   0, (byte) 255,
                 (byte) 255, (byte)  93, (byte)   0, (byte) 255,
                 (byte) 255, (byte) 255, (byte) 165, (byte) 255,
                 (byte)   0, (byte)  93, (byte) 214, (byte) 255,
                 (byte)  82, (byte) 215, (byte) 255, (byte) 255,
                 (byte)  41, (byte) 231, (byte)  41, (byte) 255
    };

  public static byte[] PALETTE_END =
    new byte[] {
                 (byte) 247, (byte) 199, (byte) 148, (byte) 255,
                 (byte) 231, (byte) 166, (byte)  90, (byte) 255,
                 (byte) 198, (byte) 125, (byte)  66, (byte) 255,
                 (byte) 156, (byte)  85, (byte)  24, (byte) 255,
                 (byte)  66, (byte)  69, (byte)  49, (byte) 255,
                 (byte)  41, (byte)  36, (byte)  24, (byte) 255,
                 (byte) 255, (byte) 255, (byte) 247, (byte) 255,
                 (byte) 165, (byte) 166, (byte) 165, (byte) 255,
                 (byte) 132, (byte) 134, (byte) 132, (byte) 255,
                 (byte) 255, (byte)   4, (byte)   0, (byte) 255,
                 (byte)   0, (byte) 255, (byte)   0, (byte) 255,
                 (byte) 255, (byte) 255, (byte)   0, (byte) 255,
                 (byte)   0, (byte)   4, (byte) 255, (byte) 255,
                 (byte) 255, (byte)   4, (byte) 255, (byte) 255,
                 (byte)   0, (byte) 255, (byte) 255, (byte) 255,
                 (byte) 255, (byte) 255, (byte) 255, (byte) 255
    };

  public static void decodeRunlength(byte[] rl, byte[] buf) {
    int length = rl.length;
    int readPos = 0;
    int writePos = 0;
    while(readPos < length) {
      short head = uint8(rl[readPos++]);
      byte value = 0;
      boolean copy;
      short x, y, z;
      if(head >= 224) {
        copy = false;
        value = 0;
        x = (short) (head - 224);
        y = uint8(rl[readPos++]);
        z = uint8(rl[readPos++]);
      } else if(head >= 208) {
        copy = false;
        value = 0;
        x = 0;
        y = (short) (head - 208);
        z = uint8(rl[readPos++]);
      } else if(head >= 192) {
        copy = false;
        value = 0;
        x = 0;
        y = 0;
        z = (short) (head - 192);
      } else if(head >= 160) {
        copy = false;
        value = rl[readPos++];
        x = (short) (head - 160);
        y = uint8(rl[readPos++]);
        z = uint8(rl[readPos++]);
      } else if(head >= 144) {
        copy = false;
        value = rl[readPos++];
        x = 0;
        y = (short) (head - 144);
        z = uint8(rl[readPos++]);
      } else if(head >= 128) {
        copy = false;
        value = rl[readPos++];
        x = 0;
        y = 0;
        z = (short) (head - 128);
      } else if(head >= 32) {
        copy = true;
        x = (short) (head - 32);
        y = uint8(rl[readPos++]);
        z = uint8(rl[readPos++]);
      } else if(head >= 16) {
        copy = true;
        x = 0;
        y = (short) (head - 16);
        z = uint8(rl[readPos++]);
      } else {
        copy = true;
        x = 0;
        y = 0;
        z = head;
      }
      int total = x * 65536 + y * 256 + z;
      int canWrite = buf.length - writePos;
      if(total > canWrite) {
        total = canWrite;
      }
      if(copy) {
        int canRead = length - readPos;
        if(total > canRead) {
          total = canRead;
        }
        for(int i = 0; i < total; i++) {
          value = rl[readPos++];
          buf[writePos++] = value;
        }
      } else {
        for(int i = 0; i < total; i++) {
          buf[writePos++] = value;
        }
      }
    }
  }

  public static void flipVertical(byte[] data, int width, int height) {
    byte[] buf = new byte[width];
    for(int i = 0; i < height / 2; i++) {
      System.arraycopy(data, i * width, buf, 0, width);
      System.arraycopy(data, (height - i - 1) * width, data, i * width, width);
      System.arraycopy(buf, 0, data, (height - i - 1) * width, width);
    }
  }

  @Nonnull
  public static BufferedImage createImage(byte[] bitmap, int width, int height, byte[] palette) {
    int[] colors = new int[256];
    for(int i = 0; i < 256; i++) {
      int r = uint8(palette[i * 4]);
      int g = uint8(palette[i * 4 + 1]);
      int b = uint8(palette[i * 4 + 2]);
      colors[i] = new Color(r, g, b).getRGB();
    }

    BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int x = 0;
    int y = 0;
    int pos = 0;
    int bitmapLength = bitmap.length;
    int pix;
    while(pos < bitmapLength) {
      pix = uint8(bitmap[pos]);
      if(pix != 0)
        ret.setRGB(x, y, colors[pix]);
      x++;
      pos++;
      if(x == width) {
        x = 0;
        y++;
      }
    }
    return ret;
  }

}
