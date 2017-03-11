package com.beijunyi.sa2016.tools.legacy.providers;

abstract class LegacyAsset {

  private final int id;

  protected LegacyAsset(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}
