package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;

public class AssetsSerializersModule extends AbstractModule {

  @Override
  protected void configure() {
    Kryo kryo = KryoFactory.getInstance();
    kryo.register(Animation.class, new AnimationSerializer());
    kryo.register(Audio.class, new AudioSerializer());
    kryo.register(Character.class, new CharacterSerializer());
    kryo.register(Image.class, new ImageSerializer());
  }

}
