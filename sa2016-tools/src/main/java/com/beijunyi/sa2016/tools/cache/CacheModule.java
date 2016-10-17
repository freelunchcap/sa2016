package com.beijunyi.sa2016.tools.cache;

import com.beijunyi.sa2016.AppConstants;
import com.google.inject.AbstractModule;
import org.mapdb.DB;
import org.mapdb.DBMaker;

public class CacheModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DB.class).toInstance(makeFileCache());
  }

  private static DB makeFileCache() {
    return DBMaker.fileDB(AppConstants.APP_HOME.toFile()).transactionEnable().make();
  }

}
