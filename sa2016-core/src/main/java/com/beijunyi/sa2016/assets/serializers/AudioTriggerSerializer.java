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
    output.writeAscii(trigger.getId());
    output.writeAscii(trigger.getFormat());
    output.writeInt(trigger.getBytes().length);
    output.writeBytes(trigger.getBytes());
  }

  @Nonnull
  @Override
  public AudioTrigger read(Kryo kryo, Input input, Class<Audio> type) {
    return new Audio(
                      input.readString(),
                      input.readString(),
                      input.readBytes(input.readInt())
    );
  }
}
