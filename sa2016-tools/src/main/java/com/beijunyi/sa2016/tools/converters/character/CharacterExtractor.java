package com.beijunyi.sa2016.tools.converters.character;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beijunyi.sa2016.utils.ThreadPoolFactory;

@Singleton
public class CharacterExtractor implements AssetExtractor {

  private static final ExecutorService THREADS = ThreadPoolFactory.getInstance();

  private final CharacterLocator locator;
  private final CharacterExtractionTaskFactory tasks;

  @Inject
  public CharacterExtractor(CharacterLocator locator, CharacterExtractionTaskFactory tasks) {
    this.locator = locator;
    this.tasks = tasks;
  }

  @Nonnull
  @Override
  public String name() {
    return "character";
  }

  @Override
  public void extract() {
    locator.assets().forEach((entry) -> THREADS.submit(tasks.newExtraction(entry)));
  }

}
