package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.concurrent.Immutable;

@Immutable
public record LegacySpriteHeader(
    int uid,
    long address,
    int size,
    int xOffset,
    int yOffset,
    int width,
    int height,
    int east,
    int south,
    int path,
    String reference,
    int map) {}
