package com.beijunyi.sa2016.tools.config;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import com.beijunyi.sa2016.tools.api.ApiModule;
import com.beijunyi.sa2016.tools.cache.CacheModule;
import com.google.common.collect.ImmutableList;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

@WebListener
public class InjectorProvider extends GuiceResteasyBootstrapServletContextListener {

  public InjectorProvider() {
    System.currentTimeMillis();
  }

  @Override
  protected List<? extends Module> getModules(ServletContext context) {
    return ImmutableList.of(
      new ApiModule(),
      new CacheModule()
    );
  }

}
