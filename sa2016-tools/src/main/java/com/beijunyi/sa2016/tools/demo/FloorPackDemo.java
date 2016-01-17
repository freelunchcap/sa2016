package com.beijunyi.sa2016.tools.demo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.cmd.EnvironmentContext;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorElementType;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorPack;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorPackManager;

import static com.beijunyi.sa2016.tools.resources.transform.floor.FloorPack.BY_PACK_SIZE;

public class FloorPackDemo {

  private final FloorPackManager analyzer;
  private final EnvironmentContext context;

  public FloorPackDemo(@Nonnull FloorPackManager analyzer, @Nonnull EnvironmentContext context) {
    this.analyzer = analyzer;
    this.context = context;
  }

  public void createDemo() throws IOException {
    List<FloorPack> packs = analyzer.createFloorPacks(FloorElementType.TILE);
    Collections.sort(packs, BY_PACK_SIZE);
    int id = 1;
    for(FloorPack pack : packs) {
      Path packDir = getFloorPackDemoDir(id++);
      outputFloorPack(packDir, pack);
    }
  }

  private void outputFloorPack(@Nonnull Path path, @Nonnull FloorPack pack) {

  }

  @Nonnull
  private Path getFloorPackDemoDir(int id) {
    return DemoConstants.getDemoDir(context).resolve("floor_pack").resolve(Integer.toString(id));
  }

}
