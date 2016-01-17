package com.beijunyi.sa2016.tools.resources.transform.palette;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.resources.legacy.structs.Palet;
import com.beijunyi.sa2016.tools.resources.legacy.structs.PaletColor;

public class PaletteTransformer {

  @Nonnull
  public static Palette transform(@Nonnull Palet palet) {
    List<PaletColor> colors = new ArrayList<>(256);
    colors.addAll(PaletteConstants.FIXED_HEAD);
    colors.addAll(palet.getColors());
    colors.addAll(PaletteConstants.FIXED_TAIL);
    return new Palette(toColorList(colors));
  }

  @Nonnull
  private static List<Color> toColorList(@Nonnull List<PaletColor> colors) {
    List<Color> ret = new ArrayList<>(colors.size());
    for(PaletColor color : colors)
      ret.add(color.toColor());
    return ret;
  }

}
