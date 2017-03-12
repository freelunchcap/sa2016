package com.beijunyi.sa2016.tools;

import static java.lang.System.getProperty;

public class ToolsVariables {

  public static final String IMAGE_FORMAT = getProperty("sa.image.format", "png");

  public static final String CLIENT_ADRN_PATTERN = getProperty("sa.client.adrn", "adrn_*.bin");
  public static final String CLIENT_REAL_PATTERN = getProperty("sa.client.real", "real_*.bin");
  public static final String CLIENT_SPR_PATTERN = getProperty("sa.client.spr", "spr_*.bin");
  public static final String CLIENT_SPRADRN_PATTERN = getProperty("sa.client.spradrn", "spradrn_*.bin");
  public static final String CLIENT_PALET_PATTERN = getProperty("sa.client.palet", "palet_1.sap");

}
