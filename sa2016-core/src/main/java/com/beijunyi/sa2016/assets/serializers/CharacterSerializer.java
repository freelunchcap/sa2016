package com.beijunyi.sa2016.assets.serializers;

import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

class CharacterSerializer extends Serializer<Character> {

  @Override
  public void write(Kryo kryo, Output output, Character character) {
    output.writeInt(character.getId());
    Map<ActType, Act> acts = character.getActs();
    output.writeByte(acts.size());
    for(Map.Entry<ActType, Act> act : acts.entrySet()) {
      output.writeByte(act.getKey().ordinal());
      kryo.writeObject(output, act.getValue());
    }
  }

  @Nonnull
  @Override
  public Character read(Kryo kryo, Input input, Class<Character> type) {
    int id = input.readInt();
    int acts = input.readByte();
    ImmutableMap.Builder<ActType, Act> animations = ImmutableMap.builder();
    for(int a = 0; a < acts; a++) {
      ActType actType = ActType.values()[input.readByte()];
      Act act = kryo.readObject(input, Act.class);
      animations.put(actType, act);
    }
    return new Character(id, animations.build());
  }

}
