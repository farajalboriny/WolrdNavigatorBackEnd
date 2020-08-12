package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import static com.worldnavigator.archeticture.constants.MapItems.getMapItems;
import static com.worldnavigator.configurations.CommandsConfiguration.getCommandsParam;

public class SellCommand implements Command {
  private final Printer printer;
  private Item item;

  public SellCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    if (player.checkTradeMode()) {
      validateItem();
      item = getMapItems().get(getCommandsParam()[1]);
      checkItem(player);
      sell(player);
      EntitiesManager.save(player);
      return;
    }
    throw new IllegalCommandException(
        "please use the trade command first to communicate with a seller");
  }

  private void sell(Player player) {
    printer.print("sold  " + item);
    player.getInventory().addGold(item.getPrice());
    player.getInventory().removeItem(item);
    printer.print("Success");
  }

  private void validateItem() throws IllegalCommandException {
    if (getCommandsParam().length < 2) {
      throw new IllegalCommandException("you didn't specify what item do you want to sell");
    }
  }

  private void checkItem(Player player) throws IllegalCommandException {
    if (player.getInventory().hasItem(item)) {
      return;
    }
    throw new IllegalCommandException("the item you are trying to sell is not in your inventory");
  }
}
