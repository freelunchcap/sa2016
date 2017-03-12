package com.beijunyi.sa2016.tools.converters.utils;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.legacy.LegacyPalet;
import com.beijunyi.sa2016.tools.legacy.LegacyResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;
import com.google.inject.Singleton;

import static com.beijunyi.sa2016.tools.converters.utils.PaletteConstants.*;
import static com.beijunyi.sa2016.tools.legacy.LegacyResourceType.PALET;

@Singleton
public class Palette {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final ImmutableList<Color> colors;

  @Inject
  Palette(LegacyResourcesProvider resources) throws IOException {
    colors = createPalette(resources.getResourceFile(PALET));
  }

  @Nonnull
  private static ImmutableList<Color> createPalette(Path file) throws IOException {
    LegacyPalet legacyPalet;
    try(Input input = new Input(Files.newInputStream(file))) {
      legacyPalet = KRYO.readObject(input, LegacyPalet.class);
    }
    return createPalette(legacyPalet);
  }

  @Nonnull
  private static ImmutableList<Color> createPalette(LegacyPalet legacyPalet) {
    ImmutableList.Builder<Color> builder = ImmutableList.builder();
    builder.addAll(PREFIX);
    builder.addAll(legacyPalet.getColors());
    builder.addAll(SUFFIX);
    return builder.build();
  }

  @Nonnull
  public Color getColor(int index) {
    return colors.get(index);
  }


}
