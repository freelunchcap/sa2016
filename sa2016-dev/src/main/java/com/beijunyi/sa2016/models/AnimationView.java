package com.beijunyi.sa2016.models;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.assets.Texture;
import com.beijunyi.sa2016.assets.Audio;
import com.beijunyi.sa2016.assets.Image;

class AnimationView {

  private final Texture texture;
  private final Collection<Image> images;
  private final Collection<Audio> audios;

  AnimationView(Texture texture, Collection<Image> images, Collection<Audio> audios) {
    this.texture = texture;
    this.images = images;
    this.audios = audios;
  }

  @Nonnull
  public Texture getTexture() {
    return texture;
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
