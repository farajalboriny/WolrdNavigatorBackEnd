package com.worldnavigator.configurations;

import com.worldnavigator.gameplay.ConsolePrinter;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandsConfiguration {
  private static final Printer printer = new ConsolePrinter();
  private static String[] commandsParam;
  private static Command command;
  private static final Map<String, Command> map =
      new HashMap<>() {
        {
          put("look", new LookCommand(printer));
          put("forward", new ForwardCommand(printer));
          put("backward", new BackwardCommand(printer));
          put("left", new LeftCommand(printer));
          put("right", new RightCommand(printer));
          put("buy", new BuyCommand(printer));
          put("playerstatus", new PlayerStatusCommand(printer));
          put("sell", new SellCommand(printer));
          put("quit", new QuitCommand());
          put("switch lights", new SwitchLightsCommand(printer));
          put("list", new ListCommand(printer));
          put("finish trade", new FinishTradeCommand(printer));
          put("use chest key", new OpenChestCommand(printer));
          put("check chest", new CheckChestCommand(printer));
          put("check mirror", new CheckMirrorCommand(printer));
          put("check painting", new CheckPaintingCommand(printer));
          put("check door", new CheckDoorCommand(printer));
          put("use door key", new OpenDoorCommand(printer));
          put("trade", new TradeCommand(printer));
          put("use flashlight", new FlashlightCommand(printer));
          put("help", new HelpCommand(printer));
        }
      };

  public static void setCommandsParam(String[] params) {
    commandsParam = params;
  }

  public static Command getCommand() {
    return command;
  }

  public static String[] getCommandsParam() {
    return commandsParam;
  }

  public static Map<String, Command> getCommands() {
    return map;
  }

  public static void setCommand(String command) {
    CommandsConfiguration.command = map.get(command);
  }

  public static void setCommand(Command command) {
    CommandsConfiguration.command = command;
  }
}
