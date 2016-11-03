package com.beijunyi.sa2016.models;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.Audio;
import com.beijunyi.sa2016.assets.Image;

class AnimationView {

  private final Animation animation;
  private final Collection<Image> images;
  private final Collection<Audio> audios;

  AnimationView(Animation animation, Collection<Image> images, Collection<Audio> audios) {
    this.animation = animation;
    this.images = images;
    this.audios = audios;
  }

  @Nonnull
  public Animation getAnimation() {
    return animation;
  }

  @Nonnull
  public Collection<Image> getImages() {
    return images;
  }

  @Nonnull
  public Collection<Audio> getAudios() {
    return audios;
  }

}
