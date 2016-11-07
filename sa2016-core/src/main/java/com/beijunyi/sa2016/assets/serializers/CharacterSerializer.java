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
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

class CharacterSerializer extends Serializer<Character> {

  @Override
  public void write(Kryo kryo, Output output, Character character) {
    output.writeAscii(character.getId());
    Table<Action, Direction, Animation> animations = character.getAnimations();
    Set<Action> rows = animations.rowKeySet();
    output.writeByte(rows.size());
    for(Action row : rows) {
      output.writeByte(row.ordinal());
      Map<Direction, Animation> cols = animations.row(row);
      output.writeByte(cols.size());
      for(Map.Entry<Direction, Animation> col : cols.entrySet()) {
        output.writeByte(col.getKey().ordinal());
        output.writeAscii(col.getValue());
      }
    }
  }

  @Nonnull
  @Override
  public Character read(Kryo kryo, Input input, Class<Character> type) {
    String id = input.readString();
    int rows = input.readByte();
    ImmutableTable.Builder<Action, Direction, String> animations = ImmutableTable.builder();
    for(int row = 0; row < rows; row++) {
      Action action = Action.values()[input.readByte()];
      int cols = input.readByte();
      for(int col = 0; col < cols; col++) {
        Direction direction = Direction.values()[input.readByte()];
        String animation = input.readString();
        animations.put(action, direction, animation);
      }
    }
    return new Character(id, animations.build());
  }

}
