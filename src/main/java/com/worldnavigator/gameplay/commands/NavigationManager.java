package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.gameplay.ConsolePrinter;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.fight.FightSetup;
import com.worldnavigator.managers.EntitiesManager;

import java.util.ArrayList;
import java.util.List;

public class NavigationManager {
  private static final Printer printer = new ConsolePrinter();

  public static void checkFloor(Player player) {
    checkFloorItems(player);
    checkFloorGold(player);
    EntitiesManager.save(player);
  }

  public static void checkFloorGold(Player player) {
    DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
    if (defaultRoom.getFloorGold() > 0) {
      player.getInventory().addGold(defaultRoom.getFloorGold());
      defaultRoom.setFloorGold(0);
      printer.print(
          "new items has and gold been found on the floor and has been added to the inventory");
    }
  }

  public static void checkFloorItems(Player player) {

    DefaultRoom defaultRoom = EntitiesManager.getRoom(player);
    if (defaultRoom.getFloorItems().size() > 0) {
      player.getInventory().addItems(defaultRoom.getFloorItems());
      defaultRoom.setFloorItems(new ArrayList<>());
      printer.print(
          "new items has and gold been found on the floor and has been added to the inventory");
    }
  }

  public static void checkPresentPlayers(Player player1) {
    List<String> list = EntitiesManager.findPlayers(player1.getMapId());
    for (String userName2 : list) {
      Player player2 = EntitiesManager.getPlayer(userName2);
      if (checkIfPlayersInTheSameRoom(player1, player2)) {
        if (player2.getInventory().calculateValue() > player1.getInventory().calculateValue()) {
          lootPlayer(player2, player1);
          EntitiesManager.removePlayer(player1);
          EntitiesManager.save(player2);
          printer.print("you faced a player and lost the fight");
        } else if (player2.getInventory().calculateValue()
            == player1.getInventory().calculateValue()) {
          FightSetup.setupFightBody(player1, player2);
          player1.setTie(true);
          player2.setTie(true);
          EntitiesManager.save(player1);
          EntitiesManager.save(player2);
          printer.print(
              "you faced a player and there is a tie please pick quickly or you will lose");
        } else {
          lootPlayer(player1, player2);
          EntitiesManager.removePlayer(player2);
          EntitiesManager.save(player1);
          printer.print("You won against a player and took all of his stuff");
        }
      }
    }
  }

  private static void lootPlayer(Player player1, Player player2) {
    player1.getInventory().addItems(player2.getInventory().getItems());
    player2.getInventory().removeItems(player2.getInventory().getItems());
    player1.getInventory().addGold(player2.getInventory().getGold());
    player2.getInventory().subtractGold(player2.getInventory().getGold());
  }

  public static boolean checkIfPlayersInTheSameRoom(Player player1, Player player2) {
    return player1.getCurrentRoomId() == player2.getCurrentRoomId()
        && !(player1.getUserName().equals(player2.getUserName()));
  }

  public static boolean checkFight(Player player, DefaultRoom room) {

    List<String> list = EntitiesManager.findPlayers(player.getMapId());
    int count = 0;
    for (String s : list) {
      Player player2 = EntitiesManager.getPlayer(s);
      if (player2.getCurrentRoomId() == room.getId()
          && !player.getUserName().equals(player2.getUserName())) {
        count++;
      }
      if (count > 1) {
        return true;
      }
    }
    return false;
  }
}
