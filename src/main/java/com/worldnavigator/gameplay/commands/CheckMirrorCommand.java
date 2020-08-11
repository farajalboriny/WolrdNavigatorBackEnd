package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.Mirror;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class CheckMirrorCommand implements Command {
  private final Printer printer;

  public CheckMirrorCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    NonPlayerCharManager.tradeModeCheck(player);
    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (defaultRoom.isLit()) {
      Mirror mirror = getMirror(player);
      if (mirror.hasKey()) {
        player.getInventory().addItem(mirror.getKey());
        printer.print("added  " + mirror.getKey().toString() + " to the inventory");
        mirror.removeKey();
        EntitiesGetter.save(player);
      } else {
        printer.print("no key found");
      }
      return;
    }
    throw new IllegalCommandException("Room is Dark");
  }

  private Mirror getMirror(Player player) throws IllegalCommandException {
    List<RoomPart> parts = PartsManager.getAvailableParts(player);
    for (RoomPart part : parts) {
      if (part instanceof Mirror) {
        return (Mirror) part;
      }
    }

    throw new IllegalCommandException("there is no Mirror here");
  }
}
