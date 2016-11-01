package com.beijunyi.sa2016.models;

import java.util.Map;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.Audio;
import com.beijunyi.sa2016.assets.Image;

public class AnimationView {

  private final Animation animation;
  private final Map<String, Image> images;
  private final Map<String, Audio> audios;

  AnimationView(Animation animation, Map<String, Image> images, Map<String, Audio> audios) {
    this.animation = animation;
    this.images = images;
    this.audios = audios;
  }

  @Nonnull
  public Animation getAnimation() {
    return animation;
  }

  @Nonnull
  public Map<String, Image> getImages() {
    return images;
  }

  @Nonnull
  public Map<String, Audio> getAudios() {
    return audios;
  }

}
