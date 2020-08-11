package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.archeticture.parts.impl.NormalDoor;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class CheckDoorCommand implements Command {
  private final Printer printer;

  public CheckDoorCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (defaultRoom.isLit()) {
      NormalDoor door = getDoor(player);
      if (door.isOpen()) {
        printer.print("Door is open");
      } else {
        printer.print("Door is Closed");
      }
      return;
    }
    throw new IllegalCommandException("Room is Dark");
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
