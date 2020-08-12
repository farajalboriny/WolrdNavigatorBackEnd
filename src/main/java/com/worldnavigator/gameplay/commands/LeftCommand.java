package com.worldnavigator.gameplay.commands;

import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class LeftCommand implements Command {
  private final Printer printer;

  public LeftCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    player.leftRotate();
    printer.print("your current direction is " + player.getOrientations());
    EntitiesManager.save(player);
  }
}
