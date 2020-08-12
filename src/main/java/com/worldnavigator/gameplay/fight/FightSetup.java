package com.worldnavigator.gameplay.fight;

import com.worldnavigator.gameplay.Player;
import com.worldnavigator.managers.FightManager;

public class FightSetup {
  public static void setupFightBody(Player player1, Player player2) {
    FightBody fightBody = new FightBody();
    fightBody.setUserName(player1.getUserName());
    FightBody fightBody2 = new FightBody();
    fightBody2.setUserName(player2.getUserName());
    addPlayersToTheFightManager(player1, player2, fightBody, fightBody2);
  }

  public static void addPlayersToTheFightManager(
      Player player1, Player player2, FightBody fightBody, FightBody fightBody2) {
    FightManager.addUser(player1.getUserName(), fightBody);
    FightManager.addUser(player2.getUserName(), fightBody2);
    FightManager.addPlayers(player1.getUserName(), player2.getUserName());
    FightManager.addPlayers(player2.getUserName(), player1.getUserName());
  }
}
