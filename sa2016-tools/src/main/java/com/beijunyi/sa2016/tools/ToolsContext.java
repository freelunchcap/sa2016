package com.beijunyi.sa2016.tools;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.getProperty;

public class ToolsContext {

  public static final Path SERVER_RESOURCES = Paths.get(getProperty("sa.server", "server"));
  public static final Path CLIENT_RESOURCES = Paths.get(getProperty("sa.client", "client"));

}
