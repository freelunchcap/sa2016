package com.beijunyi.sa2016.api;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {

  private static final ImmutableCollection<Class<?>> API_SERVICES = ImmutableList.of(CharactersApi.class, ImageApi.class);

  @Override
  protected void configure() {
    API_SERVICES.forEach(this::bind);
  }
}
