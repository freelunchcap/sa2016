package com.beijunyi.sa2016.tools.assets.provider;

import java.util.HashSet;
import java.util.Set;

import com.beijunyi.sa2016.tools.assets.model.Asset;
import com.beijunyi.sa2016.tools.cache.AssetsCache;
import com.beijunyi.sa2016.tools.extractors.AssetsExtractor;

public abstract class AssetProvider<A extends Asset> {

  private final AssetsCache<A> cache;
  private final AssetsExtractor<A> extractor;
  private final Set<String> pending = new HashSet<>();

  public AssetProvider(AssetsCache<A> cache, AssetsExtractor<A> extractor) {
    this.cache = cache;
    this.extractor = extractor;
  }

  A get(String id) {
    return null;
  }


}
