package com.beijunyi.sa2016.extraction.cmd;

import java.io.IOException;
import javax.annotation.Nonnull;

public interface CommandHandler {

  void handle(@Nonnull CommandParams params) throws IOException;

}
