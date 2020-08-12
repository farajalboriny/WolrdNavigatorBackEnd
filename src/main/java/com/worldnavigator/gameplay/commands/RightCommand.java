package com.worldnavigator.gameplay.commands;

import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class RightCommand implements Command {
  private final Printer printer;

  public RightCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);

    player.rightRotate();
    EntitiesManager.save(player);
    printer.print("your current direction is " + player.getOrientations());
  }
}
