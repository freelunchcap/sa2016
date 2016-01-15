package com.beijunyi.sa2016.extraction.cmd;

import java.util.List;
import javax.annotation.Nullable;

import com.beust.jcommander.Parameter;

public class CommandParams {

  @Parameter(names = "-count", variableArity = true)
  private List<String> count;

  @Parameter(names = "-server")
  private String server;

  @Parameter(names = "-client")
  private String client;

  @Parameter(names = "-output")
  private String output;

  @Nullable
  public List<String> getCount() {
    return count;
  }

  @Nullable
  public String getServer() {
    return server;
  }

  @Nullable
  public String getClient() {
    return client;
  }

  @Nullable
  public String getOutput() {
    return output;
  }

}
