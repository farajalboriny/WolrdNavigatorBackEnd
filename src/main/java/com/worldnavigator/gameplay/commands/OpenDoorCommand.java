package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.archeticture.parts.impl.NormalDoor;
import com.worldnavigator.configurations.GameManager;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class OpenDoorCommand implements Command {

  private final Printer printer;

  public OpenDoorCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (defaultRoom.isLit()) {
      NormalDoor door = getDoor(player);
      if (door.isWinningDoor()) {
        if (player.getInventory().hasItem(Item.winningdoorkey)) {
          playerWon(player);
          return;
        } else {
          throw new IllegalCommandException("you don't have the correct key to open this door");
        }
      }
      if (door.isOpen()) {
        printer.print("Door is already open");
        return;
      }

      if (player.getInventory().hasItem(Item.doorkey)) {
        door.setOpen(true);
        player.getInventory().removeItem(Item.doorkey);
        printer.print("you opened the door");
        EntitiesGetter.save(player);
        return;
      }
      throw new IllegalCommandException("you don't have the key to open the door");
    }
    throw new IllegalCommandException("Room is Dark");
  }

  private void playerWon(Player player) {
    player.getInventory().removeItem(Item.winningdoorkey);
    printer.print("finished");
    GameManager gameManager = new GameManager();
    gameManager.removeAllPlayers(player.getMapId());
  }

  private NormalDoor getDoor(Player player) throws IllegalCommandException {
    List<RoomPart> list = PartsManager.getAvailableParts(player);
    for (RoomPart roomPart : list) {
      if (roomPart instanceof NormalDoor) {
        return (NormalDoor) roomPart;
      }
    }
    throw new IllegalCommandException("There is no door in this direction");
  }
}
