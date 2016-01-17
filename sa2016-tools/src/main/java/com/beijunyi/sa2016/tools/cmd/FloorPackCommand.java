package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.FloorManager;
import com.beijunyi.sa2016.tools.resources.legacy.ImageManager;
import com.beijunyi.sa2016.tools.transform.floor.FloorElementType;
import com.beijunyi.sa2016.tools.transform.floor.FloorPackAnalyzer;
import com.beust.jcommander.Parameter;

public class FloorPackCommand extends AbstractCommand implements Command {

  private final FloorPackAnalyzer analyzer;

  @Parameter(names = "--type", variableArity = true)
  private Set<String> types;

  @Inject
  public FloorPackCommand(@Nonnull EnvironmentContext context, @Nonnull FloorPackAnalyzer analyzer) {
    super(context);
    this.analyzer = analyzer;
  }

  @Nonnull
  @Override
  public String getName() {
    return "floor-pack";
  }

  @Override
  public void call() throws IOException {
    analyzer.createFloorPacks(FloorElementType.OBJECT);
  }

}
