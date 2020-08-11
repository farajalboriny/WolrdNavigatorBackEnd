package com.worldnavigator.gameplay.commands;

import com.auth.model.PlayerEntity;
import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;

public class QuitCommand implements Command {

  public QuitCommand() {}

  @Override
  public void execute(Player player) {
    if (player != null) {
      DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
      if (player.getMapId() != -1) {
        defaultRoom.setFloorItems(player.getInventory().getItems());
        defaultRoom.setFloorGold(player.getInventory().getGold());
        EntitiesGetter.save(player);
        PlayerEntity playerEntity = EntitiesGetter.find(player.getUserName());
        EntitiesGetter.delete(playerEntity);
      }
    }
    if (player != null)
      if (player.getMapId() == -1) {
        PlayerEntity playerEntity = EntitiesGetter.find(player.getUserName());
        EntitiesGetter.delete(playerEntity);
      }
  }
}
