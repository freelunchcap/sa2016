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
    output.writeAscii(image.getId());
    output.writeAscii(image.getTexture());
    output.writeShort(image.getWidth());
    output.writeShort(image.getHeight());
    output.writeShort(image.getX());
    output.writeShort(image.getY());
  }

  @Nonnull
  @Override
  public Image read(Kryo kryo, Input input, Class<Image> type) {
    String id = input.readString();
    String texture = input.readString();
    int width = input.readShort();
    int height = input.readShort();
    int x = input.readShort();
    int y = input.readShort();
    return new Image(id, texture, width, height, x, y);
  }
}