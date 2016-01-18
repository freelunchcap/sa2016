package com.beijunyi.sa2016.tools.resources.transform.floor;

import java.io.IOException;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.LegacyFloorManager;
import com.beijunyi.sa2016.tools.resources.legacy.LegacyImageManager;
import com.beijunyi.sa2016.tools.resources.legacy.structs.Ls2Map;
import com.google.common.primitives.Ints;

public class FloorPackManager {

  private final LegacyFloorManager floors;
  private final LegacyImageManager images;

  @Inject
  public FloorPackManager(@Nonnull LegacyFloorManager floors, @Nonnull LegacyImageManager images) {
    this.floors = floors;
    this.images = images;
  }

  @Nonnull
  public List<FloorPack> createFloorPacks(@Nonnull FloorElementType type) throws IOException {
    List<FloorPack> ret = new LinkedList<>();
    for(Ls2Map floor : floors.getAllFloors())
      processFloor(floor, ret, type);
    return ret;
  }

  private void processFloor(@Nonnull Ls2Map floor, @Nonnull Collection<FloorPack> packs, @Nonnull FloorElementType type) throws IOException {
    List<FloorPack> candidates = new ArrayList<>();
    Set<Integer> images = getImages(floor, type);
    if(images.isEmpty())
      return;
    for(FloorPack pack : packs)
      if(pack.hasAnyImages(images)) {
        candidates.add(pack);
    }
    if(candidates.isEmpty()) {
      FloorPack newPack = new FloorPack();
      packs.add(newPack);
      candidates.add(newPack);
    }
    FloorPack first = candidates.get(0);
    first.addImages(images, floor.getId());
    for(int i = 1; i < candidates.size(); i++) {
      FloorPack other = candidates.get(i);
      first.merge(other);
      packs.remove(other);
    }
  }

  @Nonnull
  private Set<Integer> getImages(@Nonnull Ls2Map floor, @Nonnull FloorElementType type) throws IOException {
    int[] ids;
    switch(type) {
      case TILE:
        ids = floor.getTiles();
        break;
      case OBJECT:
        ids = floor.getObjects();
        break;
      default:
        throw new IllegalStateException();
    }
    return toImageIds(ids);
  }

  @Nonnull
  private Set<Integer> toImageIds(@Nonnull int[] floorElementIds) throws IOException {
    Set<Integer> ret = new HashSet<>();
    for(int id : floorElementIds)
      ret.addAll(images.getMappedImageIds(id));
    return ret;
  }


}
