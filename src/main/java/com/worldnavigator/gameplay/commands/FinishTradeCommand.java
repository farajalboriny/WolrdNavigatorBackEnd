package com.worldnavigator.gameplay.commands;

import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class FinishTradeCommand implements Command {
  private final Printer printer;

  public FinishTradeCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {

    if (player.checkTradeMode()) {
      player.switchTrade();
      printer.print("Trade mode deactivated");
      EntitiesManager.save(player);
      return;
    }
    throw new IllegalCommandException("trade mode is already off");
  }
}
