package com.beijunyi.sa2016.tools.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Animation {

  private final String id;
  private final int duration;
  private final ImmutableList<Frame> frames;
  private final ImmutableList<Integer> audios;

  public Animation(String id, int duration, ImmutableList<Frame> frames, ImmutableList<Integer> audios) {
    this.id = id;
    this.duration = duration;
    this.frames = frames;
    this.audios = audios;
  }

  public String getId() {
    return id;
  }

  public int getDuration() {
    return duration;
  }

  public List<Frame> getFrames() {
    return frames;
  }

  public List<Integer> getAudios() {
    return audios;
  }

}
