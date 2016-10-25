package com.beijunyi.sa2016.assets.repositories;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.beijunyi.sa2016.assets.Asset;
import com.beijunyi.sa2016.utils.KryoFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.mapdb.DB;
import org.mapdb.HTreeMap;

import static org.mapdb.Serializer.BYTE_ARRAY;
import static org.mapdb.Serializer.STRING_ASCII;

public abstract class AssetRepo<A extends Asset> {

  private static final Kryo KRYO = KryoFactory.getInstance();
  private final HTreeMap<String, byte[]> store;

  AssetRepo(DB cache) {
    this.store = createStore(cache);
  }

  public void put(A asset) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    KRYO.writeObject(new Output(stream), asset);
    store.put(asset.getId(), stream.toByteArray());
  }

  @Nullable
  public A get(String id) {
    byte[] data = store.get(id);
    if(data == null) return null;
    return KRYO.readObject(new Input(data), type());
  }

  @Nonnull
  protected abstract String namespace();

  @Nonnull
  protected abstract Class<A> type();

  @Nonnull
  private HTreeMap<String, byte[]> createStore(DB cache) {
    return cache.hashMap(namespace())
             .keySerializer(STRING_ASCII)
             .valueSerializer(BYTE_ARRAY)
             .create();
  }

}
