package com.worldnavigator.gameplay.commands;

import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.NonPlayerCharManager;
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
    EntitiesGetter.save(player);
    printer.print("your current direction is " + player.getOrientations());
  }
}
