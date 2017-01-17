package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Audio;
import com.beijunyi.sa2016.assets.AudioTrigger;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class AudioTriggerSerializer extends Serializer<AudioTrigger> {

  @Override
  public void write(Kryo kryo, Output output, AudioTrigger trigger) {
    output.writeShort(trigger.getFrame());
    output.writeAscii(trigger.getAudio());
  }

  @Nonnull
  @Override
  public AudioTrigger read(Kryo kryo, Input input, Class<AudioTrigger> type) {
    return new AudioTrigger(
                      input.readShort(),
                      input.readString()
    );
  }
}
