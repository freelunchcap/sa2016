package com.beijunyi.sa2016.tools.converters.images;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.converters.graphics.BitmapRenderer;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.ClientResource;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

@Singleton
class ImageExtractionTaskFactory {

  private final ResourcesProvider resources;
  private final BitmapRenderer renderer;
  private final SpriteRepo repo;

  @Inject
  public ImageExtractionTaskFactory(ResourcesProvider resources, BitmapRenderer renderer, SpriteRepo repo) {
    this.resources = resources;
    this.renderer = renderer;
    this.repo = repo;
  }

  @Nonnull
  ImageExtractionTask newExtraction(Adrn entry) {
    return new ImageExtractionTask(resources.getClientResource(ClientResource.REAL), entry, renderer, repo);
  }

}
