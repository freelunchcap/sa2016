package com.beijunyi.sa2016.tools.api;

import java.util.Collection;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;

public class ApiModule extends AbstractModule {

  private static final Collection<Class<?>> API_SERVICES = ImmutableList.of(CharactersApi.class);

  @Override
  protected void configure() {
    API_SERVICES.forEach(this::bind);
  }
}
