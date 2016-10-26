package com.beijunyi.sa2016.assets.repositories;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.beijunyi.sa2016.assets.Asset;
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

import static org.mapdb.Serializer.BYTE_ARRAY;
import static org.mapdb.Serializer.STRING_ASCII;

public abstract class AssetRepo<A extends Asset> {

  private static final Logger LOG = LoggerFactory.getLogger(AssetRepo.class);

  private static final Kryo KRYO = KryoFactory.getInstance();
  private final BTreeMap<String, byte[]> store;

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

  public int count() {
    return store.size();
  }

  @Nonnull
  public ImmutableList<A> list(@Nullable String previous, @Nullable Integer limit) {
    Iterator<Map.Entry<String, byte[]>> iterator = store.entryIterator(previous, false, null, false);
    int remain = limit == null ? -1 : limit;
    ImmutableList.Builder<A> ret = ImmutableList.builder();
    while(remain != 0 && iterator.hasNext()) {
      Map.Entry<String, byte[]> entry = iterator.next();
      A asset = readObject(entry.getValue());
      if(asset == null) {
        LOG.warn("Missing asset {}", entry.getKey());
      } else {
        ret.add(asset);
      }
      if(remain > 0) remain--;
    }
    return ret.build();
  }

  @Nullable
  public A get(String id) {
    return readObject(store.get(id));
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
  private BTreeMap<String, byte[]> createStore(DB cache) {
    return cache.treeMap(namespace())
             .keySerializer(STRING_ASCII)
             .valueSerializer(BYTE_ARRAY)
             .createOrOpen();
  }

}
