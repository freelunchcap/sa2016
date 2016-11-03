package com.beijunyi.sa2016.models;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.Animation.Frame;
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
    Animation animation = animations.get(id);
    if(animation == null) return null;
    List<Image> images = findImageResources(animation);
    return new AnimationView(animation, images, ImmutableList.of());
  }

  @Nonnull
  private List<Image> findImageResources(Animation animation) {
    return animation.getFrames()
                        .stream()
                        .map(Frame::getImage)
                        .map(images::get)
                        .collect(toList());
  }


}
