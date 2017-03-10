package com.beijunyi.sa2016.tools.converters.character;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Sprite;
import com.beijunyi.sa2016.tools.legacy.LegacyAnimation;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
class AnimationFactory {

  private static final Logger LOG = LoggerFactory.getLogger(AnimationFactory.class);

  @Nonnull
  Animation newAnimation(String id, LegacyAnimation asset) {
    ImmutableList.Builder<Texture.Frame> frames = ImmutableList.builder();
    asset.getFrames().forEach((f) -> frames.add(convert(id, f)));
    return new Texture(id, asset.getDuration(), frames.build());
  }

  @Nonnull
  private static Sprite convert(String id, LegacyAnimation.Frame frame) {
    int image = frame.getImage();
    int audio = Math.max(frame.getImpactAudio(), frame.getDodgeAudio());
    if(image < 0) {
      LOG.warn("Animation {} has invalid image id {}", id, image);
      image = 0;
    }
    if(audio < 0) {
      LOG.warn("Animation {} has invalid audio id {}", id, image);
      audio = 0;
    }
    return new Texture.Frame(image, audio);
  }

}
