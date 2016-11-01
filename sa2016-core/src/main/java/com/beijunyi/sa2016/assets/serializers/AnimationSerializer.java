package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Animation;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

class AnimationSerializer extends Serializer<Animation> {

  @Override
  public void write(Kryo kryo, Output output, Animation animation) {
    output.writeString(animation.getId());
    output.writeShort(animation.getDuration());
    output.writeShort(animation.getFrames().size());
    animation.getFrames().forEach((f) -> {
      output.writeAscii(f.getImage());
      output.writeAscii(f.getAudio());
    });
  }

  @Nonnull
  @Override
  public Animation read(Kryo kryo, Input input, Class<Animation> type) {
    String id = input.readString();
    int duration = input.readShort();
    int count = input.readShort();
    ImmutableList.Builder<Animation.Frame> frames = ImmutableList.builder();
    while(count-- > 0) {
      String image = input.readString();
      String audio = input.readString();
      frames.add(new Animation.Frame(image, audio));
    }
    return new Animation(id, duration, frames.build());
  }
}
