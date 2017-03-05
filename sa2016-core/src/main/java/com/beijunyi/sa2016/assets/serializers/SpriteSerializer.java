package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.Media;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class SpriteSerializer extends Serializer<Sprite> {

  @Override
  public void write(Kryo kryo, Output output, Sprite object) {
    output.writeInt(object.getId());
    output.writeShort(object.getWidth());
    output.writeShort(object.getHeight());
    output.writeShort(object.getXOffset());
    output.writeShort(object.getYOffset());
    kryo.writeObject(output, object.getMedia());
  }

  @Nonnull
  @Override
  public Sprite read(Kryo kryo, Input input, Class<Sprite> type) {
    int id = input.readInt();
    int width = input.readShort();
    int height = input.readShort();
    int x = input.readShort();
    int y = input.readShort();
    Media media = kryo.readObject(input, Media.class);
    return new Sprite(id, width, height, x, y, media);
  }
}