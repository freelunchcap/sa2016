package com.beijunyi.sa2016.tools.resources.transform.floor;

import java.util.*;
import javax.annotation.Nonnull;

public class FloorPack {

  public static final Comparator<FloorPack> BY_PACK_SIZE = new FloorPackComparator();

  private final Set<Integer> images;

  private FloorPack(@Nonnull Set<Integer> images) {
    this.images = images;
  }

  @Nonnull
  public static FloorPack dynamicPack() {
    return new FloorPack(new HashSet<Integer>());
  }

  @Nonnull
  public static FloorPack staticPack(@Nonnull Set<Integer> images) {
    return new FloorPack(images);
  }

  public void merge(@Nonnull FloorPack other) {
    images.addAll(other.getImages());
  }

  public boolean hasAnyImages(@Nonnull Set<Integer> mapImages) {
    return !Collections.disjoint(images, mapImages);
  }

  public void addImages(@Nonnull Collection<Integer> newImages) {
    images.addAll(newImages);
  }

  @Nonnull
  public Set<Integer> getImages() {
    return images;
  }

}
