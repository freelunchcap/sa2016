package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Asset;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.nio.file.Path;

@ThreadSafe
public interface AssetSerializer<A extends Asset> {

  void serialize(A asset, Path dir) throws IOException;

  @Nullable
  A deserialize(int id, Path dir) throws IOException;
}
