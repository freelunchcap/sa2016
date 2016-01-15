package com.beijunyi.sa2016.extraction.cmd;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import com.beijunyi.sa2016.extraction.context.EnvironmentService;
import com.beijunyi.sa2016.extraction.context.EnvironmentContext;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class CommandService {

  private final Set<CommandHandler> handlers;
  private final EnvironmentService environmentService;

  @Inject
  public CommandService(@Nonnull Set<CommandHandler> handlers, @Nonnull EnvironmentService environmentService) {
    this.handlers = handlers;
    this.environmentService = environmentService;
  }

  public void process(@Nonnull String[] args) throws IOException {
    CommandParams params = readArgs(args);
    environmentService.setContext(readContext(params));
    for(CommandHandler handler : handlers)
      handler.handle(params);
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

  @Nonnull
  private static EnvironmentContext readContext(@Nonnull CommandParams params) {
    String server = params.getServer();
    String client = params.getClient();
    String output = params.getOutput();
    return new EnvironmentContext(toPath(server), toPath(client), toPath(output));
  }

  @Nullable
  private static Path toPath(@Nullable String pathStr) {
    return pathStr != null ? Paths.get(pathStr) : null;
  }

}
