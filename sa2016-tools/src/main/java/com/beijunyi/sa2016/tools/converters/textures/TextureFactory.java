package com.beijunyi.sa2016.tools.converters.textures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.assets.Texture;
import com.beijunyi.sa2016.assets.repositories.TextureRepo;
import com.beijunyi.sa2016.tools.converters.images.ImageAsset;
import com.beijunyi.sa2016.tools.legacy.*;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.ToolsContext.IMAGE_FORMAT;
import static com.beijunyi.sa2016.tools.converters.textures.TextureUtils.decodeBitmap;
import static com.beijunyi.sa2016.tools.converters.textures.TextureUtils.makePalette;
import static com.beijunyi.sa2016.tools.legacy.ClientResource.PALET;
import static com.beijunyi.sa2016.tools.utils.BitConverter.uint8;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.System.arraycopy;

@Singleton
public class TextureFactory {

  private static final Kryo KRYO = KryoFactory.getInstance();

  private final TextureRepo textureRepo;
  private final ImmutableList<Color> colors;

  @Inject
  public TextureFactory(TextureRepo textureRepo, ResourcesProvider resources) throws IOException {
    colors = readPalette(resources.getClientResource(PALET));
    this.textureRepo = textureRepo;
  }

  @Nonnull
  public Texture createTexture(ImageAsset asset) {
    Texture texture = textureRepo.get(asset.getId());
    if(texture == null) {
      texture = renderTexture(asset);
    }
    return texture;
  }

  @Nonnull
  private Texture renderTexture(ImageAsset asset) {
    Real real = asset.getData();
    int width = real.getWidth();
    int height = real.getHeight();
    byte[] bitmap = new byte[width * height];
    readBitmap(real, bitmap);
    BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
    for(int i = 0; i < width * height; i++) {
      int color = uint8(bitmap[i]);
      if(color != 0) image.setRGB(i % width, height - 1 - i / width, colors.get(color).getRGB());
    }
    return toTexture(asset.getId(), image);
  }

  @Nonnull
  private static Texture toTexture(int id, BufferedImage image) {
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ImageIO.write(image, IMAGE_FORMAT, out);
      return new Texture(id, IMAGE_FORMAT, out.toByteArray());
    } catch(IOException e) {
      throw new IllegalStateException();
    }
  }

  private static void readBitmap(Real real, byte[] bitmap) {
    if(real.getMajor() == 1) {
      decodeBitmap(real.getData(), bitmap);
    } else {
      arraycopy(real.getData(), 0, bitmap, 0, bitmap.length);
    }
  }

  @Nonnull
  private static ImmutableList<Color> readPalette(Path file) throws IOException {
    Palet palet;
    try(Input input = new Input(Files.newInputStream(file))) {
      palet = KRYO.readObject(input, Palet.class);
    }
    return makePalette(palet);
  }

}
