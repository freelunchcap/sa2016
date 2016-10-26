package com.beijunyi.sa2016.tools.converters;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.ClientResource;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;

@Singleton
class ImageExtractionTaskFactory {

  private final ResourcesProvider resources;
  private final ImageRenderer renderer;
  private final ImageRepo repo;

  @Inject
  public ImageExtractionTaskFactory(ResourcesProvider resources, ImageRenderer renderer, ImageRepo repo) {
    this.resources = resources;
    this.renderer = renderer;
    this.repo = repo;
  }

  @Nonnull
  ImageExtractionTask newTask(Adrn adrn) {
    return new ImageExtractionTask(resources.getClientResource(ClientResource.REAL), adrn, renderer, repo);
  }

}
