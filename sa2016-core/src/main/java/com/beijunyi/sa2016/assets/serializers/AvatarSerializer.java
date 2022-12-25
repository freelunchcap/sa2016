package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Avatar;

import javax.annotation.concurrent.Immutable;
import java.nio.file.Path;

@Immutable
public final class AvatarSerializer implements AssetSerializer<Avatar> {

  @Override
  public void serialize(Avatar asset, Path dir) {}

  @Override
  public Avatar deserialize(int id, Path dir) {
    return null;
  }
}
