package com.beijunyi.sa2016.tools.converters.graphics;

import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;

public final class RunLengthDecoder {

  public static void decodeBitmap(byte[] src, byte[] bitmap) {
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

}
