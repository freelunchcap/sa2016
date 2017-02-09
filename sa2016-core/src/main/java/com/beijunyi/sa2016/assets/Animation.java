package com.beijunyi.sa2016.assets;

import java.util.List;
import javax.annotation.Nonnull;

public class Animation implements Asset {

  private final String id;
  private final String texture;
  private final int width;
  private final int height;
  private final int x;
  private final int y;
  private final int frames;
  private final int duration;
  private final List<AudioTrigger> audios;

  public Animation(String id, String texture, int width, int height, int x, int y, int frames, int duration, List<AudioTrigger> audios) {
    this.id = id;
    this.texture = texture;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    this.frames = frames;
    this.duration = duration;
    this.audios = audios;
  }

  @Nonnull
  @Override
  public String getId() {
    return id;
  }

  @Nonnull
  public String getTexture() {
    return texture;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getFrames() {
    return frames;
  }

  public int getDuration() {
    return duration;
  }

  @Nonnull
  public List<AudioTrigger> getAudios() {
    return audios;
  }

}
