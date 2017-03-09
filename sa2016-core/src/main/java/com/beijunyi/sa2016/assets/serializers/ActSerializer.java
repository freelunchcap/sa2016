package com.beijunyi.sa2016.assets.serializers;

import java.util.List;
import java.util.Map;

import com.beijunyi.sa2016.assets.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class ActSerializer extends Serializer<Act> {

  @Override
  public void write(Kryo kryo, Output output, Act object) {
    output.writeShort(object.getFrames());
    output.writeInt(object.getDuration());
    Map<Direction, SpriteSheet> animations = object.getAnimations();
    output.writeByte(animations.size());
    for(Map.Entry<Direction, SpriteSheet> animation : animations.entrySet()) {
      output.writeByte(animation.getKey().ordinal());
      kryo.writeObject(output, animation.getValue());
    }
    List<ActEffect> effects = object.getEffects();
    output.writeShort(effects.size());
    for(ActEffect effect : effects) {
      kryo.writeObject(output, effect);
    }
  }

  @Override
  public Act read(Kryo kryo, Input input, Class<Act> type) {
    int frames = input.readShort();
    int duration = input.readInt();
    int animationCount = input.readByte();
    ImmutableMap.Builder<Direction, SpriteSheet> animations = ImmutableMap.builder();
    for(int a = 0; a < animationCount; a++) {
      Direction direction = Direction.values()[input.readByte()];
      SpriteSheet animation = kryo.readObject(input, SpriteSheet.class);
      animations.put(direction, animation);
    }
    int effectCount = input.readShort();
    ImmutableList.Builder<ActEffect> effects = ImmutableList.builder();
    for(int e = 0; e < effectCount; e++) {
      ActEffect effect = kryo.readObject(input, ActEffect.class);
      effects.add(effect);
    }
    return new Act(frames, duration, animations.build(), effects.build());
  }

}
