package com.beijunyi.sa2016.extraction.cmd;

import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class CommandService {

  private final Set<CommandHandler> handlers;

  @Inject
  public CommandService(@Nonnull Set<CommandHandler> handlers) {
    this.handlers = handlers;
  }

  public void process(@Nonnull String[] args) {
    CommandParams params = readArgs(args);
    for(CommandHandler handler : handlers) {
      if(!handler.handle(params)) break;
    }
  }

  @Nonnull
  private static CommandParams readArgs(@Nonnull String[] args) {
    CommandParams params = new CommandParams();
    JCommander cmd = new JCommander(params);
    try {
      cmd.parse(args);
    } catch(ParameterException e) {
      cmd.usage();
    }
    return params;
  }

}
