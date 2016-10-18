package com.beijunyi.sa2016;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import com.google.common.collect.ImmutableList;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

@WebListener
public class InjectorProvider extends GuiceResteasyBootstrapServletContextListener {

  @Override
  protected List<? extends Module> getModules(ServletContext context) {
    return ImmutableList.of(
      new CoreModule(),
      new DevModule()
    );
  }

}
