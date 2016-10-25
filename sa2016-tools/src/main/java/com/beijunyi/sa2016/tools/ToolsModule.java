package com.beijunyi.sa2016.tools;

import com.beijunyi.sa2016.tools.cmd.CommandModule;
import com.beijunyi.sa2016.tools.converters.ExtractorModule;
import com.beijunyi.sa2016.tools.legacy.LegacyAssetsModule;
import com.google.inject.AbstractModule;

public class ToolsModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new CommandModule());
    install(new ExtractorModule());
    install(new LegacyAssetsModule());
  }

}
