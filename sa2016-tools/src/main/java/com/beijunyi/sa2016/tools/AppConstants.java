package com.beijunyi.sa2016.tools;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppConstants {

  public static final Path APP_HOME = Paths.get(System.getProperty("user.home"), "SA");

  static {
    try {
      Files.createDirectories(APP_HOME);
    } catch(Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

}
