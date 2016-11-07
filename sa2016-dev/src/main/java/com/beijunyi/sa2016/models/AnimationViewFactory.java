package com.beijunyi.sa2016.models;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Texture;
import com.beijunyi.sa2016.assets.Texture.Frame;
import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.repositories.AnimationRepo;
import com.beijunyi.sa2016.assets.repositories.AudioRepo;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.google.common.collect.ImmutableList;

import static java.util.stream.Collectors.toList;

@Singleton
public class AnimationViewFactory {

  private final AnimationRepo animations;
  private final ImageRepo images;
  private final AudioRepo audios;

  @Inject
  public AnimationViewFactory(AnimationRepo animations, ImageRepo images, AudioRepo audios) {
    this.animations = animations;
    this.images = images;
    this.audios = audios;
  }

  @Nullable
  public AnimationView animationView(String id) {
    Texture texture = animations.get(id);
    if(texture == null) return null;
    List<Image> images = findImageResources(texture);
    return new AnimationView(texture, images, ImmutableList.of());
  }

  @Nonnull
  private List<Image> findImageResources(Texture texture) {
    return texture.getFrames()
                        .stream()
                        .map(Frame::getImage)
                        .map(images::get)
                        .collect(toList());
  }


}
