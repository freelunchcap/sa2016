package com.beijunyi.sa2016.assets.repositories;

import com.beijunyi.sa2016.AppConstants;
import com.google.inject.AbstractModule;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.nio.file.Path;

import static java.lang.Runtime.getRuntime;

@NotThreadSafe
public final class AssetsRepositoriesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DB.class).toInstance(makeFileCache());
  }

  @Nonnull
  private static DB makeFileCache() {
    Path assets = AppConstants.APP_HOME.resolve("assets.bin");
    DB ret = DBMaker.fileDB(assets.toFile()).checksumHeaderBypass().make();
    getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  ret.commit();
                  ret.close();
                }));
    return ret;
  }
}
