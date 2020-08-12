package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.parts.abstracts.Seller;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import static com.worldnavigator.archeticture.constants.MapItems.getMapItems;
import static com.worldnavigator.configurations.CommandsConfiguration.getCommandsParam;

public class BuyCommand implements Command {
  private final Printer printer;
  private Seller seller;
  private Item item;

  public BuyCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    if (player.checkTradeMode()) {
      seller = NonPlayerCharManager.getSeller(player);
      validateItem();
      item = getMapItems().get(getCommandsParam()[1]);
      checkItem();
      checkPrice(player);
      buyItem(player);
      EntitiesManager.save(player);
      return;
    }
    throw new IllegalCommandException(
        "please use the trade command first to communicate with a seller");
  }

  private void buyItem(Player player) {
    seller.getItems().remove(item);
    player.getInventory().subtractGold(item.getPrice());
    player.getInventory().addItem(item);
    printer.print("Success");
  }

  private void checkPrice(Player player) throws IllegalCommandException {
    if (player.getInventory().getGold() < item.getPrice()) {
      throw new IllegalCommandException("come back when you have enough gold");
    }
  }

  private void validateItem() throws IllegalCommandException {
    if (getCommandsParam().length < 2) {
      throw new IllegalCommandException("you didn't specify what item do you want to buy");
    }
  }

  private void checkItem() throws IllegalCommandException {
    if (seller.getItems().isEmpty()) {
      throw new IllegalCommandException("Seller is out of items");
    } else if (!seller.getItems().contains(item)) {
      throw new IllegalCommandException("the seller does not have the requested item");
    }
  }
}
