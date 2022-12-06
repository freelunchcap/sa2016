package com.beijunyi.sa2016.tools.legacy;

public record LegacySpriteData(
    String signature, int major, int minor, int width, int height, byte[] data) {}
