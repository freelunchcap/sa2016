package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beust.jcommander.JCommander;

public class CommandService {

  private final JCommander commander;
  private final Map<String, Command> commandMap = new HashMap<>();

  @Inject
  public CommandService(@Nonnull Set<Command> commands) {
    this.commander = setupCommander(commands);
    for(Command command : commands)
      commandMap.put(command.getName(), command);
  }

  public void process(@Nonnull String[] args) throws IOException {
    commander.parse(args);
    Command command = commandMap.get(commander.getParsedCommand());
    command.call();
  }

  @Nonnull
  private static JCommander setupCommander(@Nonnull Set<Command> commands) {
    JCommander ret = new JCommander();
    for(Command command : commands)
      ret.addCommand(command.getName(), command);
    return ret;
  }

}
