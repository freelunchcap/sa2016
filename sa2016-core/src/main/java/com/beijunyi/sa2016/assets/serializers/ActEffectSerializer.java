package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.ActEffect;
import com.beijunyi.sa2016.assets.SoundEffect;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class ActEffectSerializer extends Serializer<ActEffect> {

  @Override
  public void write(Kryo kryo, Output output, ActEffect object) {
    output.writeShort(object.getFrame());
    output.writeBoolean(object.isKnockback());
    kryo.writeObject(output, object.getSound());
  }

  @Override
  public ActEffect read(Kryo kryo, Input input, Class<? extends ActEffect> type) {
    int frame = input.readShort();
    boolean knockback = input.readBoolean();
    SoundEffect sound = kryo.readObject(input, SoundEffect.class);
    return new ActEffect(frame, knockback, sound);
  }

}
