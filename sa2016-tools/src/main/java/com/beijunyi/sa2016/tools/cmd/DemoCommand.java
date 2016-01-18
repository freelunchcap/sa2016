package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.demo.ResourceDemo;
import com.beijunyi.sa2016.tools.resources.ResourceType;
import com.beust.jcommander.Parameter;

public class DemoCommand extends Command {

  private final Set<ResourceDemo> demos;

  @Parameter(names = "--type", variableArity = true)
  private Set<ResourceType> types;

  @Inject
  public DemoCommand(@Nonnull EnvironmentContext context, @Nonnull Set<ResourceDemo> demos) {
    super(context);
    this.demos = demos;
  }

  @Nonnull
  @Override
  public String getName() {
    return "demo";
  }

  @Override
  public void call() throws IOException {
    for(ResourceDemo demo : demos) {
      if(types == null || types.contains(demo.getResourceType()))
        demo.outputResourceDemo();
    }
  }

}
