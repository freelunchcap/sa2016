package com.beijunyi.sa2016.tools.converters;

import java.awt.*;
import java.awt.List;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.Palet;
import com.beijunyi.sa2016.tools.legacy.PaletColor;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.tools.resources.ResourceProvider;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.PALET;

@Singleton
public class ImageRenderer {

  private static final ImmutableList<Color> FIXED_HEAD
    = ImmutableList.of(
    new Color(0, 0, 0, 0),
    new Color(0, 4, 132, 255),
    new Color(0, 134, 0, 255),
    new Color(0, 134, 132, 255),
    new Color(132, 4, 0, 255),
    new Color(132, 4, 132, 255),
    new Color(132, 134, 0, 255),
    new Color(192, 192, 192, 255),
    new Color(198, 223, 198, 255),
    new Color(247, 207, 165, 255),
    new Color(0, 4, 222, 255),
    new Color(0, 93, 255, 255),
    new Color(165, 255, 255, 255),
    new Color(214, 93, 0, 255),
    new Color(255, 215, 82, 255),
    new Color(41, 231, 41, 255)
  );

  private static final ImmutableList<Color> FIXED_TAIL
    = ImmutableList.of(
    new Color(148, 199, 247, 255),
    new Color(90, 166, 231, 255),
    new Color(66, 125, 198, 255),
    new Color(24, 85, 156, 255),
    new Color(49, 69, 66, 255),
    new Color(24, 36, 41, 255),
    new Color(247, 255, 255, 255),
    new Color(165, 166, 165, 255),
    new Color(132, 134, 132, 255),
    new Color(0, 4, 255, 255),
    new Color(0, 255, 0, 255),
    new Color(0, 255, 255, 255),
    new Color(255, 4, 0, 255),
    new Color(255, 4, 255, 255),
    new Color(255, 255, 0, 255),
    new Color(255, 255, 255, 255)
  );

  private final ImmutableList<Color> colors;

  @Inject
  public ImageRenderer(ResourceProvider resources, Kryo kryo) throws IOException {
    colors = readPalette(resources.getClientResource(PALET), kryo);
  }

  @Nonnull
  public RenderedImage render(Real real) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  private static ImmutableList<Color> readPalette(Path file, Kryo kryo) throws IOException {
    Palet palet;
    try(Input input = new Input(Files.newInputStream(file))) {
      palet = kryo.readObject(input, Palet.class);
    }
    return readPalette(palet);
  }

  @Nonnull
  private static ImmutableList<Color> readPalette(Palet palet) {
    ImmutableList.Builder<Color> builder = ImmutableList.builder();
    builder.addAll(FIXED_HEAD);
    for(PaletColor pColor : palet.getColors())
      builder.add(toColor(pColor));
    builder.addAll(FIXED_TAIL);
    return builder.build();
  }

  @Nonnull
  private static Color toColor(PaletColor pColor) {
    return new Color(pColor.getRed(), pColor.getGreen(), pColor.getBlue(), 255);
  }

}
