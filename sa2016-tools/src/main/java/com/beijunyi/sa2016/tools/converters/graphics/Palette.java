package com.beijunyi.sa2016.tools.converters.graphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.legacy.LegacyPalet;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;
import com.google.inject.Singleton;

import static com.beijunyi.sa2016.tools.converters.graphics.PaletteConstants.PREFIX;
import static com.beijunyi.sa2016.tools.converters.graphics.PaletteConstants.SUFFIX;
import static com.beijunyi.sa2016.tools.legacy.ClientResource.PALET;

@Singleton
public class Palette {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final ImmutableList<Color> colors;

  @Inject
  Palette(ResourcesProvider resources) throws IOException {
    colors = createPalette(resources.getClientResource(PALET));
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
  Color getColor(int index) {
    return colors.get(index);
  }


}
