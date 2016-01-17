package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.LegacyFloorManager;
import com.beijunyi.sa2016.tools.resources.legacy.LegacyImageManager;
import com.beust.jcommander.Parameter;

public class CountCommand extends AbstractCommand implements Command {

  private final LegacyImageManager images;
  private final LegacyFloorManager floors;

  @Parameter(names = "--type", variableArity = true)
  private Set<String> types;

  @Inject
  public CountCommand(@Nonnull EnvironmentContext context, @Nonnull LegacyImageManager images, @Nonnull LegacyFloorManager floors) {
    super(context);
    this.images = images;
    this.floors = floors;
  }

  @Nonnull
  @Override
  public String getName() {
    return "count";
  }

  @Override
  public void call() throws IOException {
    if(types == null || types.contains("image"))
      System.out.println("Images: " + images.count());
    if(types == null || types.contains("floor"))
      System.out.println("Floors: " + floors.count());
  }

}
