package com.beijunyi.sa2016.assets.repositories;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.beijunyi.sa2016.assets.GameAsset;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mapdb.Serializer.*;

public abstract class AssetRepo<A extends GameAsset> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  private static final Kryo KRYO = KryoFactory.getInstance();
  private final BTreeMap<Integer, byte[]> store;

  AssetRepo(DB cache) {
    this.store = createStore(cache);
  }

  public void put(A asset) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    Output output = new Output(stream);
    KRYO.writeObject(output, asset);
    output.flush();
    store.put(asset.getId(), stream.toByteArray());
  }

  @Nonnull
  public List<A> list(@Nullable Integer start, @Nullable String dir, @Nullable Integer max) {
    Iterator<Map.Entry<Integer, byte[]>> iterator = iterate(start, dir);
    int remain = max == null ? 10 : max;
    ImmutableList.Builder<A> ret = ImmutableList.builder();
    while(remain-- > 0 && iterator.hasNext()) {
      Map.Entry<Integer, byte[]> entry = iterator.next();
      A asset = readObject(entry.getValue());
      if(asset == null) {
        LOG.warn("Missing asset {}", entry.getKey());
      } else {
        ret.add(asset);
      }
    }
    return ret.build();
  }

  @Nullable
  public A get(int id) {
    return readObject(store.get(id));
  }

  @Nonnull
  private Iterator<Map.Entry<Integer, byte[]>> iterate(@Nullable Integer start, @Nullable String dir) {
    if(dir == null) dir = "gte";
    dir = dir.toLowerCase();
    boolean inclusive = dir.endsWith("e");
    if(dir.startsWith("gt"))
      return store.entryIterator(start, inclusive, null, false);
    if(dir.startsWith("lt"))
      return store.descendingEntryIterator(null, false, start, inclusive);
    else {
      throw new IllegalArgumentException(dir);
    }
  }

  @Nullable
  private A readObject(@Nullable byte[] data) {
    if(data == null) return null;
    return KRYO.readObject(new Input(data), type());
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract Class<A> type();

  @Nonnull
  private BTreeMap<Integer, byte[]> createStore(DB cache) {
    return cache.treeMap(namespace())
             .keySerializer(INTEGER)
             .valueSerializer(BYTE_ARRAY)
             .createOrOpen();
  }

}
