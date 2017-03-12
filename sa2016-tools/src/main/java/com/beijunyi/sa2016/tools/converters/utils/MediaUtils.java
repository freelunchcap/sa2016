package com.beijunyi.sa2016.tools.converters.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;

import com.beijunyi.sa2016.assets.Media;

import static com.beijunyi.sa2016.tools.ToolsVariables.IMAGE_FORMAT;

public final class MediaUtils {

  @Nonnull
  public static Media create(BufferedImage image) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, IMAGE_FORMAT, out);
    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
    return new Media(IMAGE_FORMAT, out.toByteArray());
  }

}
