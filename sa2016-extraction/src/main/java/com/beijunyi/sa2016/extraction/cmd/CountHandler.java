package com.beijunyi.sa2016.extraction.cmd;

import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.resources.legacy.LegacyImageManager;
import com.beijunyi.sa2016.extraction.resources.legacy.LegacyResourceService;

public class CountHandler implements CommandHandler {

  private final LegacyImageManager images;

  @Inject
  public CountHandler(@Nonnull LegacyImageManager images) {
    this.images = images;
  }

  @Override
  public void handle(@Nonnull CommandParams params) throws IOException {
    List<String> countables = params.getCount();
    if(countables == null)
      return;
    images.count();
  }

}
