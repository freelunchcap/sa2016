package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Asset;
import java.io.InputStream;
import java.io.OutputStream;

public interface AssetSerializer<A extends Asset> {

  void write(A asset, OutputStream out);

  A read(InputStream in);
}
