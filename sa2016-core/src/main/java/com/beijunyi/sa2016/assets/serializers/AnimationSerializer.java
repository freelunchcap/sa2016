package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Animation;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

public class AnimationSerializer extends Serializer<Animation> {

  @Override
  public void write(Kryo kryo, Output output, Animation animation) {
    output.writeString(animation.getId());
    output.writeShort(animation.getDuration());
    output.writeShort(animation.getFrames());
    animation.getImages().forEach(output::writeAscii);
    animation.getAudios().forEach(output::writeAscii);
  }

  @Nonnull
  @Override
  public Animation read(Kryo kryo, Input input, Class<Animation> type) {
    String id = input.readString();
    int duration = input.readShort();
    int frames = input.readShort();
    ImmutableList.Builder<String> images = ImmutableList.builder();
    for(int f = 0; f < frames; f++) images.add(input.readString());
    ImmutableList.Builder<String> audios = ImmutableList.builder();
    for(int f = 0; f < frames; f++) audios.add(input.readString());
    return new Animation(id, duration, frames, images.build(), audios.build());
  }
}
