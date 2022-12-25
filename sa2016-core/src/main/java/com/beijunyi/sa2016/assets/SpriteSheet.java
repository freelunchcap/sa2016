package com.beijunyi.sa2016.assets;

import javax.annotation.concurrent.Immutable;

@Immutable
public record SpriteSheet(
    int frameWidth, int frameHeight, int frames, int xOffset, int yOffset, Media media) {}
