package com.beijunyi.sa2016.tools.legacy;

import com.google.common.collect.ImmutableList;

import javax.annotation.concurrent.Immutable;

@Immutable
public record LegacyAnimation(
    int direction,
    int action,
    int duration,
    int length,
    ImmutableList<LegacyAnimationFrame> frames) {}
