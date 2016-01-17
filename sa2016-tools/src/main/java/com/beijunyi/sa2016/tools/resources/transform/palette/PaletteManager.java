package com.beijunyi.sa2016.tools.resources.transform.palette;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.LegacyPaletteManager;
import com.beijunyi.sa2016.tools.resources.legacy.structs.Palet;

public class PaletteManager {

  private final LegacyPaletteManager legacy;

  private final Map<Integer, Palette> palettes = new HashMap<>();

  @Inject
  public PaletteManager(@Nonnull LegacyPaletteManager legacy) {
    this.legacy = legacy;
  }

  @Nonnull
  public Palette getPalette(int id) throws IOException {
    Palette ret = palettes.get(id);
    if(ret != null)
      return ret;
    synchronized(this) {
      ret = palettes.get(id);
      if(ret == null) {
        Palet legacyPalet = legacy.getResource(id);
        ret = PaletteTransformer.transform(legacyPalet);
        palettes.put(id, ret);
      }
      return ret;
    }
  }


}
