package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;

public class SwitchLightsCommand implements Command {
  private final Printer printer;

  public SwitchLightsCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) {
    DefaultRoom currentRoom = EntitiesManager.getRoom(player);
    if (currentRoom.hasSwitch()) {
      currentRoom.turnSwitch();
      printer.print("clicked on light switch");
      EntitiesManager.save(player);
      return;
    }
    printer.print("this room does not have a light switch!");
  }
}
