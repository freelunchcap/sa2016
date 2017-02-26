package com.beijunyi.sa2016.assets;

public class Image implements Asset {

  private final int id;
  private final int texture;
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public Image(int id, int texture, int width, int height, int x, int y) {
    this.id = id;
    this.texture = texture;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  @Override
  public int getId() {
    return id;
  }

  public int getTexture() {
    return texture;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}
