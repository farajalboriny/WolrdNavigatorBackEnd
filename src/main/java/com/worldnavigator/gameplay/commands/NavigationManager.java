package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.gameplay.ConsolePrinter;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.fight.FightSetup;

import java.util.ArrayList;
import java.util.List;

public class NavigationManager {
  private static final Printer printer = new ConsolePrinter();

  public static void checkFloor(Player player) {
    checkFloorItems(player);
    checkFloorGold(player);
    EntitiesGetter.save(player);
  }

  public static void checkFloorGold(Player player) {
    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (defaultRoom.getFloorGold() > 0) {
      player.getInventory().addGold(defaultRoom.getFloorGold());
      defaultRoom.setFloorGold(0);
      printer.print(
          "new items has and gold been found on the floor and has been added to the inventory");
    }
  }

  public static void checkFloorItems(Player player) {

    DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
    if (defaultRoom.getFloorItems().size() > 0) {
      player.getInventory().addItems(defaultRoom.getFloorItems());
      defaultRoom.setFloorItems(new ArrayList<>());
      printer.print(
          "new items has and gold been found on the floor and has been added to the inventory");
    }
  }

  public static void checkPresentPlayers(Player player1) {
    DefaultRoom player1Room = EntitiesGetter.getRoom(player1);
    List<String> list = EntitiesGetter.findPlayers(player1.getMapId());
    for (String userName2 : list) {
      Player player2 = EntitiesGetter.getPlayer(userName2);
      DefaultRoom player2Room = EntitiesGetter.getRoom(player2);
      if (checkIfPlayersInTheSameRoom(player1Room, player2Room, player1, player2)) {
        if (player2.getInventory().calculateValue() > player1.getInventory().calculateValue()) {
          lootPlayer(player2, player1);
          EntitiesGetter.removePlayer(player1);
          EntitiesGetter.save(player1);
          printer.print("you faced a player and lost the fight");
        } else if (player2.getInventory().calculateValue()
            == player1.getInventory().calculateValue()) {
          FightSetup.setupFightBody(player1, player2);
          player1.setTie(true);
          player2.setTie(true);
          EntitiesGetter.save(player1);
          EntitiesGetter.save(player2);
          printer.print(
              "you faced a player and there is a tie please pick quickly or you will lose");
        } else {
          lootPlayer(player1, player2);
          EntitiesGetter.removePlayer(player2);
          EntitiesGetter.save(player1);
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

  public static boolean checkIfPlayersInTheSameRoom(
      DefaultRoom player1Room, DefaultRoom player2Room, Player player1, Player player2) {
    return player2Room.getId() == player1Room.getId()
        && !player1.getUserName().equals(player2.getUserName());
  }

  public static boolean checkFight(Player player, DefaultRoom room) {

    List<String> list = EntitiesGetter.findPlayers(player.getMapId());
    int count = 0;
    for (String s : list) {
      Player player2 = EntitiesGetter.getPlayer(s);
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
