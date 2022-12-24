package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Character;

import javax.annotation.concurrent.Immutable;
import java.nio.file.Path;

@Immutable
public final class CharacterSerializer implements AssetSerializer<Character> {

  @Override
  public void serialize(Character asset, Path dir) {}

  @Override
  public Character deserialize(Path dir) {
    return null;
  }
}
