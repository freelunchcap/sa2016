package com.beijunyi.sa2016.tools.resources.transform.image;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.assets.model.Bitmap;
import com.beijunyi.sa2016.tools.assets.model.Frame;
import com.beijunyi.sa2016.tools.resources.legacy.structs.Adrn;
import com.beijunyi.sa2016.tools.resources.legacy.structs.LegacyImageObject;
import com.beijunyi.sa2016.tools.resources.legacy.structs.Real;

import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.lang.System.arraycopy;

public class ImageTransformer {

  @Nonnull
  public static Frame transform(@Nonnull LegacyImageObject legacy) {
    Adrn adrn = legacy.getAdrn();
    Real real = legacy.getReal();
    int width = adrn.getWidth();
    int height = adrn.getHeight();
    byte[] pixels = real.getMajor() == 1 ? decode(real.getData(), width * height) : real.getData();
    verticalFlip(pixels, width, height);
    Bitmap bitmap = new Bitmap(width, height, pixels);
    return new Frame(adrn.getxOffset(), adrn.getyOffset(), bitmap);
  }

  @Nonnull
  private static byte[] decode(@Nonnull byte[] src, int size) {
    byte[] ret = new byte[size];
    int length = src.length;
    int readPos = 0;
    int writePos = 0;
    while(readPos < length) {
      short head = uint8(src[readPos++]);
      byte value = 0;
      boolean copy;
      short x, y, z;
      if(head >= 224) {
        copy = false;
        value = 0;
        x = (short) (head - 224);
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 208) {
        copy = false;
        value = 0;
        x = 0;
        y = (short) (head - 208);
        z = uint8(src[readPos++]);
      } else if(head >= 192) {
        copy = false;
        value = 0;
        x = 0;
        y = 0;
        z = (short) (head - 192);
      } else if(head >= 160) {
        copy = false;
        value = src[readPos++];
        x = (short) (head - 160);
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 144) {
        copy = false;
        value = src[readPos++];
        x = 0;
        y = (short) (head - 144);
        z = uint8(src[readPos++]);
      } else if(head >= 128) {
        copy = false;
        value = src[readPos++];
        x = 0;
        y = 0;
        z = (short) (head - 128);
      } else if(head >= 32) {
        copy = true;
        x = (short) (head - 32);
        y = uint8(src[readPos++]);
        z = uint8(src[readPos++]);
      } else if(head >= 16) {
        copy = true;
        x = 0;
        y = (short) (head - 16);
        z = uint8(src[readPos++]);
      } else {
        copy = true;
        x = 0;
        y = 0;
        z = head;
      }
      int total = x * 65536 + y * 256 + z;
      int canWrite = size - writePos;
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
          ret[writePos++] = value;
        }
      } else {
        for(int i = 0; i < total; i++) {
          ret[writePos++] = value;
        }
      }
    }
    return ret;
  }

  private static void verticalFlip(byte[] data, int width, int height) {
    byte[] buf = new byte[width];
    for(int i = 0; i < height / 2; i++) {
      arraycopy(data, i * width, buf, 0, width);
      arraycopy(data, (height - i - 1) * width, data, i * width, width);
      arraycopy(buf, 0, data, (height - i - 1) * width, width);
    }
  }

}
