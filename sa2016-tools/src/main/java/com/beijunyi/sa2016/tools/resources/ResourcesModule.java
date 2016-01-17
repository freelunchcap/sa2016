package com.beijunyi.sa2016.tools.resources;

import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.resources.legacy.*;
import com.esotericsoftware.kryo.Kryo;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ResourcesModule extends AbstractModule {

  private static final Collection<Class<? extends ResourceManager>> LEGACY_RESOURCE_MANAGERS
    = Arrays.<Class<? extends ResourceManager>>asList(
                     LegacyFloorManager.class,
                     LegacyImageManager.class,
                     LegacyPaletteManager.class
  );

  @Override
  protected void configure() {
    bind(Kryo.class).toInstance(setupKryo());

    Multibinder<ResourceManager> resourceManagerBinder = Multibinder.newSetBinder(binder(), ResourceManager.class);
    for(Class<? extends ResourceManager> resourceManager : LEGACY_RESOURCE_MANAGERS)
      resourceManagerBinder.addBinding().to(resourceManager);
  }

  @Nonnull
  private static Kryo setupKryo() {
    Kryo ret = new Kryo();
    ret.setReferences(false);
    return ret;
  }

}
