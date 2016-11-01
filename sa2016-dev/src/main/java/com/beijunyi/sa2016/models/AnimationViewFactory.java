package com.beijunyi.sa2016.models;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.assets.Image;
import com.beijunyi.sa2016.assets.repositories.AnimationRepo;
import com.beijunyi.sa2016.assets.repositories.AudioRepo;
import com.beijunyi.sa2016.assets.repositories.ImageRepo;
import com.google.common.collect.ImmutableMap;

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
    ImmutableMap.Builder<String, Image> imagesMap = ImmutableMap.builder();
    for(Animation.Frame frame : animation.getFrames()) {
      String imageId = frame.getImage();
      Image image = images.get(imageId);
      if(image != null) imagesMap.put(imageId, image);
    }
    return new AnimationView(animation, imagesMap.build(), ImmutableMap.of());
  }


}
