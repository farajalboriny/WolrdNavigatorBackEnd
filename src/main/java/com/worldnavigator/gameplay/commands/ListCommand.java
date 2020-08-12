package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.parts.abstracts.Seller;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class ListCommand implements Command {
  private final Printer printer;

  public ListCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    if (player.checkTradeMode()) {
      Seller seller = NonPlayerCharManager.getSeller(player);
      printer.print(seller.getItems());
      return;
    }
    throw new IllegalCommandException(
        "please use the trade command first to communicate with a seller");
  }
}
