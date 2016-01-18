package com.beijunyi.sa2016.tools.demo;

import java.util.Arrays;
import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class DemoModule extends AbstractModule {

  private static final Collection<Class<? extends ResourceDemo>> LEGACY_RESOURCE_DEMOS
    = Arrays.<Class<? extends ResourceDemo>>asList(
                     FloorPackDemo.class
  );

  @Override
  protected void configure() {
    Multibinder<ResourceDemo> resourceDemoBinder = Multibinder.newSetBinder(binder(), ResourceDemo.class);
    for(Class<? extends ResourceDemo> resourceDemo : LEGACY_RESOURCE_DEMOS)
      resourceDemoBinder.addBinding().to(resourceDemo);
  }

}
