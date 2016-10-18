package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;

public class AssetSerializersModule extends AbstractModule {

  @Override
  protected void configure() {
    Kryo kryo = getProvider(Kryo.class).get();
    kryo.register(Animation.class, new AnimationSerializer());
    kryo.register(Audio.class, new AudioSerializer());
    kryo.register(Character.class, new CharacterSerializer());
    kryo.register(Image.class, new ImageSerializer());
  }

}
