package com.worldnavigator.gameplay.commands;

import com.worldnavigator.configurations.CommandsConfiguration;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;

public class HelpCommand implements Command {
    private final Printer printer;

    public HelpCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(Player player) {
        printer.print("List of all commands");
        printer.print(CommandsConfiguration.getCommands().keySet().toString());
        printer.print("when trading with a seller please make sure you are in trade mode");
        printer.print(
                "after you are done trading please exit trade mode by using finish trade command");
        printer.print("when buying or selling please make sure to type the name correctly");
    }
}
