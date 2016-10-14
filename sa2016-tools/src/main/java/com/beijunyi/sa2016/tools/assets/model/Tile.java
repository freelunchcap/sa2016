package com.beijunyi.sa2016.tools.assets.model;

public class Tile implements Asset {

  private final String id;
  private final Frame frame;
  private final boolean reachable;

  public Tile(String id, Frame frame, boolean reachable) {
    this.id = id;
    this.frame = frame;
    this.reachable = reachable;
  }

  @Override
  public String getId() {
    return id;
  }

  public Frame getFrame() {
    return frame;
  }

  public boolean isReachable() {
    return reachable;
  }

}
