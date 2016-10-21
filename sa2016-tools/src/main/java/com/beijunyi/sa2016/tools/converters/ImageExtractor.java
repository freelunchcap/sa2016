package com.beijunyi.sa2016.tools.converters;

import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repository.ImageRepo;

@Singleton
public class ImageExtractor {

  private final ImageLocator locator;
  private final ImageRenderer renderer;
  private final ImageRepo repo;

  @Inject
  public ImageExtractor(ImageLocator locator, ImageRenderer renderer, ImageRepo repo) {
    this.locator = locator;
    this.renderer = renderer;
    this.repo = repo;
  }

  public void extractAll() {
    Iterator<ImageAsset> image = locator.imageResources();
    while(image.hasNext()) {

    }
  }


}
