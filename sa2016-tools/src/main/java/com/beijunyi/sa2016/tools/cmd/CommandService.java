package com.beijunyi.sa2016.tools.cmd;

import java.io.IOException;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beust.jcommander.JCommander;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class CommandService {

  private final JCommander commander;
  private final ImmutableMap<String, Command> lookup;

  @Inject
  public CommandService(Set<Command> commands) {
    this.commander = setupCommander(commands);
    this.lookup = Maps.uniqueIndex(commands, Command::getName);
  }

  public void process(String[] args) throws IOException {
    commander.parse(args);
    Command command = lookup.get(commander.getParsedCommand());
    command.call();
  }

  @Nonnull
  private static JCommander setupCommander(Set<Command> commands) {
    JCommander ret = new JCommander();
    for(Command command : commands)
      ret.addCommand(command.getName(), command);
    return ret;
  }

}
