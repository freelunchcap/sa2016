package com.beijunyi.sa2016.tools.converters.sprite;

import java.io.IOException;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.assets.repositories.SpriteRepo;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySprite;
import com.beijunyi.sa2016.tools.legacy.providers.LegacySpriteProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SpriteExtractionTask implements Supplier<Sprite> {

  private static final Logger LOG = LoggerFactory.getLogger(SpriteExtractionTask.class);

  private final int id;
  private final LegacySpriteProvider assets;
  private final SpriteFactory factory;
  private final SpriteRepo repo;

  SpriteExtractionTask(int id, LegacySpriteProvider assets, SpriteFactory factory, SpriteRepo repo) {
    this.id = id;
    this.assets = assets;
    this.factory = factory;
    this.repo = repo;
  }

  @Nonnull
  @Override
  public Sprite get() {
    Sprite sprite = repo.get(id);
    if(sprite == null) {
      LegacySprite asset = assets.get(id);
      try {
        sprite = factory.create(asset);
      } catch(IOException e) {
        LOG.error("Could not create sprite {}", id);
        throw new IllegalStateException(e);
      }
      repo.put(sprite);
    }
    return sprite;
  }

}
