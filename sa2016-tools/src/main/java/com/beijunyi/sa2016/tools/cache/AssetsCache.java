package com.beijunyi.sa2016.tools.cache;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerArray;

public abstract class AssetsCache<A> {

  private final HTreeMap<String, A> store;

  public AssetsCache(DB cache) {
    this.store = createStore(cache);
  }

  protected abstract String namespace();

  protected abstract Serializer<A> serializer();

  public boolean contains(String id) {
    return store.containsKey(id);
  }

  public A get(String id) {
    return store.get(id);
  }

  private HTreeMap<String, A> createStore(DB cache) {
    return cache.hashMap(namespace())
             .keySerializer(SerializerArray.STRING_ASCII)
             .valueSerializer(serializer())
             .create();
  }

}
