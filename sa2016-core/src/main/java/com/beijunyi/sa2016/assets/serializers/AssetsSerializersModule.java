package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.*;
import com.beijunyi.sa2016.assets.Character;
import com.google.inject.AbstractModule;

import static com.beijunyi.sa2016.utils.KryoFactory.register;

public class AssetsSerializersModule extends AbstractModule {

  @Override
  protected void configure() {
    register(ActEffect.class, new ActEffectSerializer());
    register(Character.class, new CharacterSerializer());
    register(Media.class, new MediaSerializer());
    register(SoundEffect.class, new SoundEffectSerializer());
    register(Sprite.class, new SpriteSerializer());
    register(SpriteSheet.class, new SpriteSheetSerializer());
  }

}
