package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Sprite;

import javax.annotation.concurrent.Immutable;
import java.nio.file.Path;

@Immutable
public final class SpriteSerializer implements AssetSerializer<Sprite> {

  @Override
  public void serialize(Sprite asset, Path dir) {}

  @Override
  public Sprite deserialize(Path dir) {
    return null;
  }
}