package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.Mirror;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class LookCommand implements Command {
  private final Printer printer;

  public LookCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (!defaultRoom.isLit()) throw new IllegalCommandException("Room is Dark");
    List<RoomPart> parts = PartsManager.getAvailableParts(player);

    for (RoomPart part : parts) {
      if (part instanceof Mirror) {
        printer.print("you see a reflection of yourself");
      } else {
        printer.print(part.toString());
      }
    }
  }
}
