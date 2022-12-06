package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.SpriteSheet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class SpriteSheetSerializer extends Serializer<SpriteSheet> {

  @Override
  public void write(Kryo kryo, Output output, SpriteSheet object) {
    output.writeShort(object.getFrameWidth());
    output.writeShort(object.getFrameHeight());
    output.writeShort(object.getFrames());
    output.writeShort(object.getXOffset());
    output.writeShort(object.getYOffset());
    kryo.writeObject(output, object.getMedia());
  }

  @Nonnull
  @Override
  public SpriteSheet read(Kryo kryo, Input input, Class<? extends SpriteSheet> type) {
    int frameWidth = input.readShort();
    int frameHeight = input.readShort();
    int frames = input.readShort();
    int xOffset = input.readShort();
    int yOffset = input.readShort();
    Media media = kryo.readObject(input, Media.class);
    return new SpriteSheet(frameWidth, frameHeight, frames, xOffset, yOffset, media);
  }

}