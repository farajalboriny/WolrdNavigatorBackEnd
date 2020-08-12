package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
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

public class OpenChestCommand implements Command {
  Printer printer;

  public OpenChestCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
    if (defaultRoom.isLit()) {
      NormalChest chest = getChest(player);
      if (chest.needsKey()) {
        if (player.getInventory().hasItem(Item.CHEST_KEY)) {
          chest.openChest();
          player.getInventory().removeItem(Item.CHEST_KEY);
          EntitiesManager.save(player);
          printer.print("you opened the chest");
          return;
        }
        throw new IllegalCommandException("you don't have a key to open the chest");
      }
      printer.print("chest already open");
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
}
