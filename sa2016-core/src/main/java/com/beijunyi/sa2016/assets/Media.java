package com.beijunyi.sa2016.assets;

import javax.annotation.concurrent.Immutable;

@Immutable
public record Media(String format, byte[] data) {}
