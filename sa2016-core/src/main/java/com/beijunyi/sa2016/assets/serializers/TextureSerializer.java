package com.beijunyi.sa2016.assets.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Texture;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TextureSerializer extends Serializer<Texture> {

  @Override
  public void write(Kryo kryo, Output output, Texture texture) {
    output.writeAscii(texture.getId());
    output.writeAscii(texture.getFormat());
    output.writeInt(texture.getData().length);
    output.writeBytes(texture.getData());
  }

  @Nonnull
  @Override
  public Texture read(Kryo kryo, Input input, Class<Texture> type) {
    String id = input.readString();
    String format = input.readString();
    byte[] data = new byte[input.readInt()];
    input.readBytes(data);
    return new Texture(id, format, data);
  }

}
