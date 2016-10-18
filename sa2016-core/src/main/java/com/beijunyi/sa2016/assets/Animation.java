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
  private final int frames;
  private final ImmutableList<String> images;
  private final ImmutableList<String> audios;

  public Animation(String id, int duration, int frames, ImmutableList<String> images, ImmutableList<String> audios) {
    this.id = id;
    this.duration = duration;
    this.frames = frames;
    this.images = images;
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

  public int getFrames() {
    return frames;
  }

  @Nonnull
  public List<String> getImages() {
    return images;
  }

  @Nonnull
  public List<String> getAudios() {
    return audios;
  }

}
