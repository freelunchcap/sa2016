package com.beijunyi.sa2016.tools.resources.transform.floor;

import java.util.*;
import javax.annotation.Nonnull;

public class FloorPack {

  public static final Comparator<FloorPack> BY_PACK_SIZE = new FloorPackComparator();

  private final Set<Integer> images = new HashSet<>();
  private final Set<Integer> floors = new HashSet<>();

  public void merge(@Nonnull FloorPack other) {
    images.addAll(other.getImages());
    floors.addAll(other.getFloors());
  }

  public boolean hasAnyImages(@Nonnull Set<Integer> mapImages) {
    return !Collections.disjoint(images, mapImages);
  }

  public void addImages(@Nonnull Collection<Integer> newImages, int floor) {
    images.addAll(newImages);
    floors.add(floor);
  }

  @Nonnull
  public Set<Integer> getImages() {
    return images;
  }

  @Nonnull
  public Set<Integer> getFloors() {
    return floors;
  }

}
