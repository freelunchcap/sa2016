package com.beijunyi.sa2016.tools.resources.legacy.structs;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class Spr implements KryoSerializable {

  private int direction;
  private int action;
  private int duration;
  private int length;
  private List<SprFrame> frames;

  public int getDirection() {
    return direction;
  }

  public int getAction() {
    return action;
  }

  public int getDuration() {
    return duration;
  }

  public int getLength() {
    return length;
  }

  @Nonnull
  public List<SprFrame> getFrames() {
    return frames;
  }

  @Override
  public void write(@Nonnull Kryo kryo, @Nonnull Output output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(@Nonnull Kryo kryo, @Nonnull Input input) {
    direction = LE.uint16(input);
    action = LE.uint16(input);
    duration = (int) LE.uint32(input);
    length = (int) LE.uint32(input);
    frames = new ArrayList<>(length);
    for(int i = 0; i < length; i++)
      frames.add(kryo.readObject(input, SprFrame.class));
  }
  
}
