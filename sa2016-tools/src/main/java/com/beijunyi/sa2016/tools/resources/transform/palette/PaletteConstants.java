package com.beijunyi.sa2016.tools.resources.transform.palette;

import java.util.Arrays;
import java.util.List;

import com.beijunyi.sa2016.tools.legacy.PaletColor;

import static com.beijunyi.sa2016.tools.legacy.PaletColor.forRGBA;

public class PaletteConstants {

  public static List<PaletColor> FIXED_HEAD
    = Arrays.asList(
                     forRGBA(0, 0, 0, 0),
                     forRGBA(0, 4, 132, 255),
                     forRGBA(0, 134, 0, 255),
                     forRGBA(0, 134, 132, 255),
                     forRGBA(132, 4, 0, 255),
                     forRGBA(132, 4, 132, 255),
                     forRGBA(132, 134, 0, 255),
                     forRGBA(192, 192, 192, 255),
                     forRGBA(198, 223, 198, 255),
                     forRGBA(247, 207, 165, 255),
                     forRGBA(0, 4, 222, 255),
                     forRGBA(0, 93, 255, 255),
                     forRGBA(165, 255, 255, 255),
                     forRGBA(214, 93, 0, 255),
                     forRGBA(255, 215, 82, 255),
                     forRGBA(41, 231, 41, 255)
  );

  public static List<PaletColor> FIXED_TAIL
    = Arrays.asList(
                     forRGBA(148, 199, 247, 255),
                     forRGBA(90, 166, 231, 255),
                     forRGBA(66, 125, 198, 255),
                     forRGBA(24, 85, 156, 255),
                     forRGBA(49, 69, 66, 255),
                     forRGBA(24, 36, 41, 255),
                     forRGBA(247, 255, 255, 255),
                     forRGBA(165, 166, 165, 255),
                     forRGBA(132, 134, 132, 255),
                     forRGBA(0, 4, 255, 255),
                     forRGBA(0, 255, 0, 255),
                     forRGBA(0, 255, 255, 255),
                     forRGBA(255, 4, 0, 255),
                     forRGBA(255, 4, 255, 255),
                     forRGBA(255, 255, 0, 255),
                     forRGBA(255, 255, 255, 255)
  );

}
