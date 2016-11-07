package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.google.inject.AbstractModule;

public class AssetsSerializersModule extends AbstractModule {

  @Override
  protected void configure() {
    KryoFactory.register(Animation.class, new AnimationSerializer());
    KryoFactory.register(Audio.class, new AudioSerializer());
    KryoFactory.register(Character.class, new CharacterSerializer());
    KryoFactory.register(Image.class, new ImageSerializer());
  }

}
