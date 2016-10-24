package com.beijunyi.sa2016.tools.cmd;

import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Singleton
public class ExtractCommand extends Command {

  private final ImmutableMap<String, AssetExtractor> lookup;

  @Inject
  public ExtractCommand(Set<AssetExtractor> extractors) {
    lookup = Maps.uniqueIndex(extractors, AssetExtractor::name);
  }

  @Nonnull
  @Override
  public String getName() {
    return "extract";
  }

  @Override
  public void call() {
    lookup.values().forEach(AssetExtractor::extract);
  }

}
