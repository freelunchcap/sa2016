package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Image;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class ImageSerializer extends Serializer<Image> {

  @Override
  public void write(Kryo kryo, Output output, Image image) {
    output.writeInt(image.getId());
    output.writeShort(image.getWidth());
    output.writeShort(image.getHeight());
    output.writeShort(image.getX());
    output.writeShort(image.getY());
    output.writeAscii(image.getFormat());
    output.writeInt(image.getBitmap().length);
    output.writeBytes(image.getBitmap());
  }

  @Nonnull
  @Override
  public Image read(Kryo kryo, Input input, Class<Image> type) {
    int id = input.readInt();
    int width = input.readShort();
    int height = input.readShort();
    int x = input.readShort();
    int y = input.readShort();
    String format = input.readString();
    byte[] bitmap = new byte[input.readInt()];
    input.readBytes(bitmap);
    return new Image(id, width, height, x, y, format, bitmap);
  }
}