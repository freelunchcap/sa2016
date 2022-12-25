package com.beijunyi.sa2016.assets;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.Immutable;

@Immutable
public record Avatar(int id, ImmutableMap<ActType, Act> acts) implements Asset {}
