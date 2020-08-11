package com.worldnavigator.gameplay;

import com.worldnavigator.configurations.CommandsConfiguration;
import com.worldnavigator.gameplay.commands.Command;
import com.worldnavigator.gameplay.exceptions.WrongCommandException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandLineParser implements Parser {
  private final List<String> multipartCommand =
      new ArrayList<>() {
        {
          add("buy");
          add("sell");
        }
      };

  @Override
  public Command parse(String command) throws WrongCommandException {
    String[] fullCommand = command.split(" ");
    if (multipartCommand.contains(fullCommand[0])) {
      CommandsConfiguration.setCommand(fullCommand[0]);
      command = fullCommand[0];
    } else {
      CommandsConfiguration.setCommand(command);
    }
    CommandsConfiguration.setCommandsParam(fullCommand);
    Map<String, Command> commands = CommandsConfiguration.getCommands();
    if (!commands.containsKey(command)) {
      throw new WrongCommandException("Wrong Command,for list of commands type help");
    }

    return commands.get(command.toLowerCase());
  }
}
