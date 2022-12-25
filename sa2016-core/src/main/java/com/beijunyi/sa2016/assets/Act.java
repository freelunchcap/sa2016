package com.beijunyi.sa2016.assets;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.Immutable;

@Immutable
public record Act(int frames, int duration, ImmutableMap<Direction, SpriteSheet> animations) {}
