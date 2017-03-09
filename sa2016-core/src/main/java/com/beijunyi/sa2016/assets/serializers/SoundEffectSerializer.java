package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.SoundEffect;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class SoundEffectSerializer extends Serializer<SoundEffect> {

  @Override
  public void write(Kryo kryo, Output output, SoundEffect object) {
    output.writeInt(object.getDuration());
    kryo.writeObject(output, object.getMedia());
  }

  @Nonnull
  @Override
  public SoundEffect read(Kryo kryo, Input input, Class<SoundEffect> type) {
    int duration = input.readInt();
    Media media = kryo.readObject(input, Media.class);
    return new SoundEffect(duration, media);
  }
}