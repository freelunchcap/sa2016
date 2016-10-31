package com.beijunyi.sa2016.tools.cmd;

import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beust.jcommander.Parameter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.mapdb.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Runtime.getRuntime;

@Singleton
class ExtractCommand extends Command {

  private static final Logger LOG = LoggerFactory.getLogger(ExtractCommand.class);

  private final ImmutableMap<String, AssetExtractor> lookup;
  private final DB store;

  @Parameter(names = "--types", variableArity = true)
  private List<String> types;

  @Inject
  public ExtractCommand(Set<AssetExtractor> extractors, DB store) {
    this.lookup = Maps.uniqueIndex(extractors, AssetExtractor::name);
    this.store = store;
  }

  @Nonnull
  @Override
  public String getName() {
    return "extract";
  }

  @Override
  public void call() {
    doExtract();
    saveAtExit();
  }

  private void doExtract() {
    if(types == null) extractAll();
    else types.forEach(this::extractType);
  }

  private void extractAll() {
    lookup.values().forEach(AssetExtractor::extract);
  }

  private void extractType(String type) {
    AssetExtractor extractor = lookup.get(type);
    if(extractor == null) LOG.warn("Skip unrecognized type {}", type);
    else extractor.extract();
  }

  private void saveAtExit() {
    getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        store.commit();
        store.close();
      }
    });
  }

}
