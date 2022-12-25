package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Media;
import com.beijunyi.sa2016.assets.Sprite;
import com.google.gson.Gson;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Immutable
public final class SpriteSerializer implements AssetSerializer<Sprite> {

  private static final String METADATA_JSON = "metadata.json";
  private static final String SPRITE_PNG = "sprite.png";

  private final Gson gson = new Gson();

  @Override
  public void serialize(Sprite asset, Path dir) throws IOException {
    try (Writer writer = Files.newBufferedWriter(dir.resolve(METADATA_JSON))) {
      Metadata metadata = new Metadata();
      metadata.width = asset.getWidth();
      metadata.height = asset.getHeight();
      metadata.xOffset = asset.getXOffset();
      metadata.yOffset = asset.getYOffset();
      gson.toJson(metadata, writer);
    }

    try (OutputStream output = Files.newOutputStream(dir.resolve(SPRITE_PNG))) {
      output.write(asset.getMedia().data());
    }
  }

  @Nullable
  @Override
  public Sprite deserialize(int id, Path dir) throws IOException {
    Path spritePngFile = dir.resolve(SPRITE_PNG);
    if (!Files.isRegularFile(spritePngFile)) {
      return null;
    }
    byte[] data;
    try (InputStream input = Files.newInputStream(spritePngFile)) {
      data = input.readAllBytes();
    }

    Path metadataJsonFile = dir.resolve(METADATA_JSON);
    if (!Files.isRegularFile(metadataJsonFile)) {
      return null;
    }
    Metadata metadata;
    try (Reader reader = Files.newBufferedReader(metadataJsonFile)) {
      metadata = gson.fromJson(reader, Metadata.class);
    }

    Media media = new Media("png", data);
    return new Sprite(
        id, metadata.width, metadata.height, metadata.xOffset, metadata.yOffset, media);
  }

  @NotThreadSafe
  public static final class Metadata {

    public int width;
    public int height;
    public int xOffset;
    public int yOffset;
  }
}
