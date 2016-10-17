package com.beijunyi.sa2016.assets;

import java.util.List;

import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

public class Animation implements Asset {

  private final String id;
  private final int duration;
  private final ImmutableList<String> frames;
  private final ImmutableList<String> audios;

  public Animation(String id, int duration, ImmutableList<String> frames, ImmutableList<String> audios) {
    this.id = id;
    this.duration = duration;
    this.frames = frames;
    this.audios = audios;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  public int getDuration() {
    return duration;
  }

  @Nonnull
  public List<String> getFrames() {
    return frames;
  }

  @Nonnull
  public List<String> getAudios() {
    return audios;
  }

}
