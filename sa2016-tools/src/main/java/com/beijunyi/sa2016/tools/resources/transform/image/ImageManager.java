package com.beijunyi.sa2016.tools.resources.transform.image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.assets.Frame;
import com.beijunyi.sa2016.tools.resources.legacy.LegacyImageManager;
import com.beijunyi.sa2016.tools.resources.legacy.structs.LegacyImageObject;

public class ImageManager {

  private final LegacyImageManager legacy;

  private Map<Integer, Frame> images = new HashMap<>();

  @Inject
  public ImageManager(@Nonnull LegacyImageManager legacy) {
    this.legacy = legacy;
  }

  @Nonnull
  public Frame getImage(int id) throws IOException {
    Frame ret = images.get(id);
    if(ret != null)
      return ret;
    synchronized(this) {
      ret = images.get(id);
      if(ret == null) {
        LegacyImageObject legacyImg = legacy.getResource(id);
        ret = ImageTransformer.transform(legacyImg);
        images.put(id, ret);
      }
      return ret;
    }
  }


}
