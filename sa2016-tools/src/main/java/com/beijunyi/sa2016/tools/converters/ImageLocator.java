package com.beijunyi.sa2016.tools.converters;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.beijunyi.sa2016.tools.legacy.Adrn;
import com.beijunyi.sa2016.tools.legacy.Real;
import com.beijunyi.sa2016.tools.legacy.ResourcesProvider;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.common.collect.ImmutableMap;

import static com.beijunyi.sa2016.tools.legacy.ClientResource.ADRN;
import static com.beijunyi.sa2016.tools.legacy.ClientResource.REAL;
import static java.nio.file.StandardOpenOption.READ;

@Singleton
public class ImageLocator {

  private final ImmutableMap<Integer, Adrn> adrns;
  private final ImmutableMap<Integer, Integer> tileIdMap;
  private final SeekableByteChannel real;
  private final Kryo kryo;

  @Inject
  public ImageLocator(ResourcesProvider resources, Kryo kryo) throws IOException {
    this.adrns = readAdrns(resources.getClientResource(ADRN), kryo);
    this.tileIdMap = indexTiles(adrns.values());
    this.real = FileChannel.open(resources.getClientResource(REAL), READ);
    this.kryo = kryo;
  }

  @Nonnull
  public Iterator<ImageAsset> imageResources() {
    return new ImageAssetIterator(adrns.values().iterator());
  }

  @Nullable
  public ImageAsset getTileImage(int tileId) {
    int imageId = tileIdMap.get(tileId);
    Adrn adrn = adrns.get(imageId);
    return getImage(adrn);
  }

  @Nullable
  private ImageAsset getImage(Adrn adrn) {
    synchronized(real) {
      try {
        real.position(adrn.getAddress());
        Input input = new Input(Channels.newInputStream(real));
        return new ImageAsset(adrn, kryo.readObject(input, Real.class));
      } catch(IOException e) {
        return null;
      }
    }
  }

  @Nonnull
  private static ImmutableMap<Integer, Adrn> readAdrns(Path file, Kryo kryo) throws IOException {
    ImmutableMap.Builder<Integer, Adrn> adrns = ImmutableMap.builder();
    try(Input input = new Input(Files.newInputStream(file))) {
      while(!input.eof()) {
        Adrn adrn = kryo.readObject(input, Adrn.class);
        adrns.put(adrn.getUid(), adrn);
      }
    }
    return adrns.build();
  }

  @Nonnull
  private static ImmutableMap<Integer, Integer> indexTiles(Collection<Adrn> adrns) {
    ImmutableMap.Builder<Integer, Integer> builder = ImmutableMap.builder();
    for(Adrn adrn : adrns) builder.put(adrn.getMapId(), adrn.getUid());
    return builder.build();
  }

  class ImageAssetIterator implements Iterator<ImageAsset> {

    final Iterator<Adrn> adrn;

    ImageAssetIterator(Iterator<Adrn> adrn) {
      this.adrn = adrn;
    }

    @Override
    public boolean hasNext() {
      return adrn.hasNext();
    }

    @Nullable
    @Override
    public ImageAsset next() {
      return getImage(adrn.next());
    }
  }

}
