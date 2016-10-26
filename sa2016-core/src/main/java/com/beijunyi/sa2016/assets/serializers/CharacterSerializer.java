package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Action;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.assets.Direction;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableTable;

class CharacterSerializer extends Serializer<Character> {

  @Override
  public void write(Kryo kryo, Output output, Character character) {
    output.writeAscii(character.getId());
    output.writeShort(character.getActions());
    for(int a = 0; a < character.getActions(); a++) {
      Action action = Action.values()[a];
      for(Direction direction : Direction.values()) {
        output.writeAscii(character.getAnimations().get(action, direction));
      }
    }
  }

  @Nonnull
  @Override
  public Character read(Kryo kryo, Input input, Class<Character> type) {
    String id = input.readString();
    int actions = input.readShort();
    ImmutableTable.Builder<Action, Direction, String> animations = ImmutableTable.builder();
    for(int a = 0; a < actions; a++) {
      Action action = Action.values()[a];
      for(Direction direction : Direction.values()) {
        animations.put(action, direction, input.readString());
      }
    }
    return new Character(id, actions, animations.build());
  }

}
