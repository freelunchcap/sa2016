package com.beijunyi.sa2016.serializers;

import com.beijunyi.sa2016.assets.Image;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class ImageSerializer extends Serializer<Image> {
  @Override
  public void write(Kryo kryo, Output output, Image img) {
    output.writeAscii(img.getId());
    output.writeAscii(img.getFormat());
    output.writeShort(img.getX());
    output.writeShort(img.getY());
    output.writeShort(img.getWidth());
    output.writeShort(img.getHeight());
    output.writeInt(img.getData().length);
    output.writeBytes(img.getData());
  }

  @Override
  public Image read(Kryo kryo, Input input, Class<Image> type) {
    return new Image(
                      input.readString(),
                      input.readString(),
                      input.readShort(),
                      input.readShort(),
                      input.readShort(),
                      input.readShort(),
                      input.readBytes(input.readInt())
    );
  }
}