package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.ResourceManager;
import com.beijunyi.sa2016.tools.resources.ResourceType;
import com.beust.jcommander.Parameter;

public class CountCommand extends AbstractCommand implements Command {

  private final Set<ResourceManager> resourceManagers;

  @Parameter(names = "--type", variableArity = true)
  private Set<String> types;

  @Inject
  public CountCommand(@Nonnull EnvironmentContext context, @Nonnull Set<ResourceManager> resourceManagers) {
    super(context);
    this.resourceManagers = resourceManagers;
  }

  @Nonnull
  @Override
  public String getName() {
    return "count";
  }

  @Override
  public void call() throws IOException {
    for(ResourceManager resourceManager : resourceManagers) {
      ResourceType type = resourceManager.getResourceType();
      if(types == null || types.contains(resourceManager.toString()))
        System.out.println("Resource " + type + ": " + resourceManager.count());
    }
  }

}
