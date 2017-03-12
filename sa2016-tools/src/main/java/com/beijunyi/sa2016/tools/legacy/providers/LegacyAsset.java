package com.beijunyi.sa2016.tools.legacy.providers;

public abstract class LegacyAsset {

  private final int id;

  LegacyAsset(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}
