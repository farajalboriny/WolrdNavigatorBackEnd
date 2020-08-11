package com.worldnavigator.gameplay.fight;

import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;

import java.util.ArrayList;

public class RockPaperScissor {

  public static String caseScissorResult(FightBody fightBody, FightBody fightBody2) {
    String enemyChosen = fightBody2.getChosen();
    if (enemyChosen.equals("paper")) {
      FightManager.getUsersFightBody().get(fightBody.getUserName()).setChosen(null);
      return "badChoice";
    } else if (enemyChosen.equals("scissor")) {
      FightManager.cleanUsersInFight(fightBody.getUserName());
      return "tie";
    } else {
      Player player = EntitiesGetter.getPlayer(fightBody.getUserName());
      player.setTie(false);
      Player player2 = EntitiesGetter.getPlayer(fightBody2.getUserName());
      win(player, player2);
      FightManager.cleanUsersInFight(fightBody.getUserName());

      return "won";
    }
  }

  public static String casePaperResult(FightBody fightBody, FightBody fightBody2) {
    String enemyChosen = fightBody2.getChosen();
    if (enemyChosen.equals("paper")) {
      FightManager.getUsersFightBody().get(fightBody.getUserName()).setChosen(null);
      return "tie";
    } else if (enemyChosen.equals("scissor")) {

      FightManager.cleanUsersInFight(fightBody.getUserName());
      return "badChoice";
    } else {
      Player player = EntitiesGetter.getPlayer(fightBody.getUserName());
      player.setTie(false);
      Player player2 = EntitiesGetter.getPlayer(fightBody2.getUserName());
      win(player, player2);
      FightManager.cleanUsersInFight(fightBody.getUserName());

      return "won";
    }
  }

  public static String caseRockResult(FightBody fightBody, FightBody fightBody2) {
    String enemyChosen = fightBody2.getChosen();
    if (enemyChosen.equals("paper")) {
      FightManager.cleanUsersInFight(fightBody.getUserName());
      return "badChoice";
    } else if (enemyChosen.equals("scissor")) {
      Player player = EntitiesGetter.getPlayer(fightBody.getUserName());
      player.setTie(false);
      Player player2 = EntitiesGetter.getPlayer(fightBody2.getUserName());
      win(player, player2);
      FightManager.cleanUsersInFight(fightBody.getUserName());

      return "won";
    } else {
      FightManager.getUsersFightBody().get(fightBody.getUserName()).setChosen(null);
      return "tie";
    }
  }

  public static void win(Player player, Player player2) {
    lootEnemyStuff(player, player2);
    removeEnemyStuff(player2);
    EntitiesGetter.getRoom(player).setFloorGold(0);
    EntitiesGetter.getRoom(player).setFloorItems(new ArrayList<>());
    EntitiesGetter.save(player);
    EntitiesGetter.save(player2);
  }

  public static void lootEnemyStuff(Player player, Player player2) {
    player.getInventory().addGold(player2.getInventory().getGold());
    player.getInventory().addItems(player2.getInventory().getItems());
  }

  public static void removeEnemyStuff(Player player2) {
    player2.getInventory().subtractGold(player2.getInventory().getGold());
    player2.getInventory().removeItems(player2.getInventory().getItems());
  }
}
