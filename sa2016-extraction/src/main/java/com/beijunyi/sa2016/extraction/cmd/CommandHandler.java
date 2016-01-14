package com.beijunyi.sa2016.extraction.cmd;

import javax.annotation.Nonnull;

public interface CommandHandler {

  boolean handle(@Nonnull CommandParams params);

}
