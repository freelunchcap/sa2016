package com.beijunyi.sa2016.tools;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ToolsContext {

  public static final Path SERVER_RESOURCES = Paths.get(System.getProperty("sa.server", "server"));
  public static final Path CLIENT_RESOURCES = Paths.get(System.getProperty("sa.client", "client"));

}
