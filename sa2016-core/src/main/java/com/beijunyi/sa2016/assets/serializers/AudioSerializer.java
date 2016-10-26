package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Audio;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

class AudioSerializer extends Serializer<Audio> {

  @Override
  public void write(Kryo kryo, Output output, Audio audio) {
    output.writeAscii(audio.getId());
    output.writeAscii(audio.getFormat());
    output.writeInt(audio.getBytes().length);
    output.writeBytes(audio.getBytes());
  }

  @Nonnull
  @Override
  public Audio read(Kryo kryo, Input input, Class<Audio> type) {
    return new Audio(
                      input.readString(),
                      input.readString(),
                      input.readBytes(input.readInt())
    );
  }
}
