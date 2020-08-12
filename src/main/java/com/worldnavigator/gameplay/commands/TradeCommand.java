package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class TradeCommand implements Command {
  private final Printer printer;

  public TradeCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {

    DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
    if (player.checkTradeMode()) {
      throw new IllegalCommandException("trade mode is already active");
    }
    if (defaultRoom.isLit()) {
      NonPlayerCharManager.getSeller(player);
      player.switchTrade();
      printer.print("Trade mode activated");
      EntitiesManager.save(player);
      return;
    }
    throw new IllegalCommandException("Room is Dark");
  }
}
