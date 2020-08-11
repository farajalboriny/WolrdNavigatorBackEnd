package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

public class FlashlightCommand implements Command {
  private final Printer printer;

  public FlashlightCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) throws IllegalCommandException {
    DefaultRoom currentRoom = EntitiesGetter.getRoom(player);
    if (player.getInventory().hasItem(Item.flashlight)) {
      if (currentRoom.isLit() && currentRoom.hasSwitch()) {
        printer.print("the room is already lit, using a flashlight won't do anything");
        return;
      }
      currentRoom.flipLit();
      printer.print("flashlight state has been flipped");
      EntitiesGetter.save(player);
      return;
    }
    throw new IllegalCommandException("you don't have a flashlight to use");
  }
}
