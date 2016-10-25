package com.beijunyi.sa2016.assets.repositories;

import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.sa2016.AppConstants;
import com.google.inject.AbstractModule;
import org.mapdb.DB;
import org.mapdb.DBMaker;

public class AssetsRepositoriesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DB.class).toInstance(makeFileCache());
  }

  @Nonnull
  private static DB makeFileCache() {
    Path assets = AppConstants.APP_HOME.resolve("assets.bin");
    return DBMaker.fileDB(assets.toFile()).transactionEnable().make();
  }


}
