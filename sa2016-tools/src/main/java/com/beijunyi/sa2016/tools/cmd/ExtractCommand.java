package com.beijunyi.sa2016.tools.cmd;

import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.converters.AssetExtractor;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Parameters
class ExtractCommand extends Command {

  private static final Logger LOG = LoggerFactory.getLogger(ExtractCommand.class);

  private final Provider<Set<AssetExtractor>> extractors;
  
  @Parameter(names = "--types", variableArity = true)
  private List<String> types;

  @Inject
  public ExtractCommand(Provider<Set<AssetExtractor>> extractors) {
    this.extractors = extractors;
  }

  @Nonnull
  @Override
  public String getName() {
    return "extract";
  }

  @Override
  public void call() {
    doExtract();
  }

  private void doExtract() {
    if(types == null) extractAll();
    else types.forEach(this::extractType);
  }

  private void extractAll() {
    extractors.get().forEach(AssetExtractor::extract);
  }

  private void extractType(String type) {
    AssetExtractor extractor = Maps.uniqueIndex(extractors.get(), AssetExtractor::name)
                                 .get(type);
    if(extractor == null) LOG.warn("Skip unrecognized type {}", type);
    else extractor.extract();
  }

}
