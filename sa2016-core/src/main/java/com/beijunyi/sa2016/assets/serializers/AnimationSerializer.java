package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.AudioTrigger;
import com.beijunyi.sa2016.assets.Texture;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

class AnimationSerializer extends Serializer<Animation> {

  @Override
  public void write(Kryo kryo, Output output, Animation animation) {
    output.writeAscii(animation.getTexture());
    output.writeShort(animation.getWidth());
    output.writeShort(animation.getHeight());
    output.writeShort(animation.getX());
    output.writeShort(animation.getY());
    output.writeShort(animation.getFrames());
    output.writeShort(animation.getDuration());
    output.writeShort(animation.getAudios().size());
    animation.getAudios().forEach((a) -> kryo.writeObject(output, a));
  }

  @Nonnull
  @Override
  public Animation read(Kryo kryo, Input input, Class<Animation> type) {
    String texture = input.readString();
    int width = input.readShort();
    int height = input.readShort();
    int x = input.readShort();
    int y = input.readShort();
    int frames = input.readShort();
    int duration = input.readShort();
    int count = input.readShort();
    ImmutableList.Builder<AudioTrigger> audios = ImmutableList.builder();
    while(count-- > 0) audios.add(kryo.readObject(input, AudioTrigger.class));
    return new Animation(texture, width, height, x, y, frames, duration, audios.build());
  }
}
