package com.beijunyi.sa2016.tools.extractors;

import java.util.List;

import com.beijunyi.sa2016.tools.assets.model.Asset;

public abstract class AssetsExtractor<A extends Asset> {

  public abstract List<String> list();

  public abstract A get(String id);

}
