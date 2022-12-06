package com.beijunyi.sa2016.tools.legacy;

import javax.annotation.concurrent.Immutable;

@Immutable
public record LegacyCharacterHeader(int id, long address, int animations, int sound) {}
