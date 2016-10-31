package com.beijunyi.sa2016.tools.converters.characters;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Animation;
import com.beijunyi.sa2016.tools.legacy.Spr;
import com.beijunyi.sa2016.tools.utils.Base62;
import com.google.common.collect.ImmutableList;

@Singleton
class AnimationFactory {

  @Nonnull
  Animation newAnimation(String id, Spr asset) {
    ImmutableList.Builder<Animation.Frame> frames = ImmutableList.builder();
    asset.getFrames().forEach((f) -> frames.add(convert(f)));
    return new Animation(id, asset.getDuration(), frames.build());
  }

  @Nonnull
  private static Animation.Frame convert(Spr.Frame frame) {
    int image = frame.getImage();
    int audio = Math.max(frame.getImpactAudio(), frame.getDodgeAudio());
    if(image < 0)
      throw new IllegalArgumentException("Invalid image id " + image);
    if(audio < 0)
      throw new IllegalArgumentException("Invalid audio id " + audio);

    return new Animation.Frame(Base62.encode(image), Base62.encode(audio));
  }

}
