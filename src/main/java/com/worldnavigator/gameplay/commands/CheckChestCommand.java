package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.managers.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.archeticture.parts.impl.NormalChest;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.managers.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class CheckChestCommand implements Command {
  Printer printer;

  public CheckChestCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
    if (defaultRoom.isLit()) {
      NormalChest chest = getChest(player);
      if (chest.needsKey()) {
        throw new IllegalCommandException("the chest is closed");
      }
      lootChest(chest, player);
      chest.getItems().clear();
      chest.setGold(0);
      EntitiesManager.save(player);
      return;
    }
    throw new IllegalCommandException("Room is Dark");
  }

  private NormalChest getChest(Player player) throws IllegalCommandException {
    List<RoomPart> parts = PartsManager.getAvailableParts(player);
    for (RoomPart part : parts) {
      if (part instanceof NormalChest) {
        return (NormalChest) part;
      }
    }
    throw new IllegalCommandException("there is no Chest here");
  }

  private void lootChest(NormalChest chest, Player player) {
    player.getInventory().addItems(chest.getItems());

    player.getInventory().addGold(chest.getGold());
    printer.print(
        "added " + chest.getGold() + "gold" + " added the following items" + chest.getItems());
  }
}
