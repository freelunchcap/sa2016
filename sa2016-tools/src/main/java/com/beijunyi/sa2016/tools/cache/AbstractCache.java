package com.beijunyi.sa2016.tools.cache;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public abstract class AbstractCache<K, V> {

  private final HTreeMap<K, V> store;

  public AbstractCache(DB cache) {
    this.store = createStore(cache);
  }

  protected abstract String namespace();

  protected abstract Serializer<K> keySerializer();

  protected abstract Serializer<V> valueSerializer();

  private HTreeMap<K, V> createStore(DB cache) {
    return cache.hashMap(namespace())
             .keySerializer(keySerializer())
             .valueSerializer(valueSerializer())
             .create();
  }

}
