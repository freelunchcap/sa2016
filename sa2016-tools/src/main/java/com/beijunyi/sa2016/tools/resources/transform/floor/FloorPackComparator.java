package com.beijunyi.sa2016.tools.resources.transform.floor;

import java.util.Comparator;
import javax.annotation.Nonnull;

public class FloorPackComparator implements Comparator<FloorPack> {

  @Override
  public int compare(@Nonnull FloorPack o1, @Nonnull FloorPack o2) {
    return Integer.compare(o2.getImages().size(), o1.getImages().size());
  }

}
