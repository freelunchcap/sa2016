package com.beijunyi.sa2016.tools.cache;

import java.util.List;

import com.beijunyi.sa2016.tools.assets.model.Asset;
import com.beijunyi.sa2016.tools.extractors.AssetsExtractor;
import com.google.common.collect.ImmutableList;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerArray;

public abstract class AssetsCache<A extends Asset> {

  private final HTreeMap<String, A> store;
  private final AssetsExtractor<A> extractor;

  public AssetsCache(DB cache, AssetsExtractor<A> extractor) {
    this.store = createStore(cache);
    this.extractor = extractor;
    prepareStore();
  }

  protected abstract String namespace();

  protected abstract Serializer<A> serializer();

  public List<String> list() {
    return ImmutableList.copyOf(store.getKeys());
  }

  public A get(String id) {
    A ret = store.get(id);
    if(ret == null) {
      synchronized(store) {
        if((ret = store.get(id)) == null) {
          ret = extractor.get(id);
          store.put(id, ret);
        }
      }
    }
    return ret;
  }

  private HTreeMap<String, A> createStore(DB cache) {
    return cache.hashMap(namespace())
             .keySerializer(SerializerArray.STRING_ASCII)
             .valueSerializer(serializer())
             .create();
  }

  private void prepareStore() {
    if(!store.isEmpty()) return;
    for(String key : extractor.list()) store.put(key, null);
  }

}
