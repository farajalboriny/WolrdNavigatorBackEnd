package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.managers.EntitiesManager;

public class QuitCommand implements Command {

  public QuitCommand() {}

  @Override
  public void execute(Player player) {
    if (player != null) {
      DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
      if (player.getMapId() != -1) {
        defaultRoom.setFloorItems(player.getInventory().getItems());
        defaultRoom.setFloorGold(player.getInventory().getGold());
        EntitiesManager.save(player);
        EntitiesManager.removePlayer(player);
      }
    }
    if (player != null)
      if (player.getMapId() == -1) {
        EntitiesManager.removePlayer(player);
      }
  }
}
