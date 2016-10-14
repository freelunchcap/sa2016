package com.beijunyi.sa2016.tools.demo;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.cmd.EnvironmentContext;
import com.beijunyi.sa2016.tools.resources.ResourceType;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorElementType;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorPack;
import com.beijunyi.sa2016.tools.resources.transform.floor.FloorPackManager;
import com.beijunyi.sa2016.tools.assets.model.Bitmap;
import com.beijunyi.sa2016.tools.resources.transform.image.ImageManager;
import com.beijunyi.sa2016.tools.assets.model.Frame;
import com.beijunyi.sa2016.tools.resources.transform.palette.Palette;
import com.beijunyi.sa2016.tools.resources.transform.palette.PaletteManager;
import com.beijunyi.sa2016.tools.utils.IOUtils;

import static com.beijunyi.sa2016.tools.demo.DemoConstants.DEMO_IMAGE_FORMAT;
import static com.beijunyi.sa2016.tools.demo.DemoConstants.DEMO_PALETTE;
import static com.beijunyi.sa2016.tools.resources.ResourceType.FLOOR_PACK;
import static com.beijunyi.sa2016.tools.resources.transform.floor.FloorPack.BY_PACK_SIZE;

public class FloorPackDemo implements ResourceDemo {

  private final FloorPackManager floorPacks;
  private final ImageManager images;
  private final PaletteManager palettes;
  private final EnvironmentContext context;

  @Inject
  public FloorPackDemo(@Nonnull FloorPackManager floorPacks, @Nonnull ImageManager images, @Nonnull PaletteManager palettes, @Nonnull EnvironmentContext context) {
    this.floorPacks = floorPacks;
    this.images = images;
    this.palettes = palettes;
    this.context = context;
  }

  @Nonnull
  @Override
  public ResourceType getResourceType() {
    return FLOOR_PACK;
  }

  @Override
  public void outputResourceDemo() throws IOException {
    Palette palette = palettes.getPalette(DEMO_PALETTE);
    int id = 1;
    for(FloorPack pack : FloorPackManager.STATIC_PACKS) {
      Path packDir = getFloorPackDemoDir(id++);
      outputFloorPack(packDir, pack, palette);
    }
    List<FloorPack> packs = floorPacks.createFloorPacks(FloorElementType.TILE);
    Collections.sort(packs, BY_PACK_SIZE);
    for(FloorPack pack : packs) {
      Path packDir = getFloorPackDemoDir(id++);
      outputFloorPack(packDir, pack, palette);
    }
  }

  private void outputFloorPack(@Nonnull Path path, @Nonnull FloorPack pack, @Nonnull Palette palette) throws IOException {
    IOUtils.forceDelete(path);
    Files.createDirectories(path);
    for(int imageId : pack.getImages()) {
      Path imagePath = path.resolve(imageId + "." + DEMO_IMAGE_FORMAT);
      Frame imageObj = images.getImage(imageId);
      outputImage(imagePath, imageObj, palette);
    }
  }

  private void outputImage(@Nonnull Path path, @Nonnull Frame imageObj, @Nonnull Palette palette) throws IOException {
    Bitmap bitmap = imageObj.getBitmap();
    RenderedImage rendered = palette.render(bitmap);
    try(OutputStream stream = Files.newOutputStream(path)) {
      ImageIO.write(rendered, DEMO_IMAGE_FORMAT, stream);
    }
  }

  @Nonnull
  private Path getFloorPackDemoDir(int id) {
    return DemoConstants.getDemoDir(context).resolve("floor_pack").resolve(Integer.toString(id));
  }

}
