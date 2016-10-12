package com.beijunyi.sa2016.tools.config;

import javax.servlet.annotation.WebListener;

import com.beijunyi.sa2016.tools.api.ApiModule;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import static com.google.inject.Guice.createInjector;

@WebListener
public class InjectorProvider extends GuiceServletContextListener {

  public InjectorProvider() {
    System.currentTimeMillis();
  }

  @Override
  protected Injector getInjector() {
    return GuiceHolder.INSTANCE;
  }

  private static class GuiceHolder {
    private static final Injector INSTANCE = createInjector(
      new ApiModule()
    );
  }


}
