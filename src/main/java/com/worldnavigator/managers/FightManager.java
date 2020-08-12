package com.worldnavigator.managers;

import com.worldnavigator.gameplay.fight.FightBody;
import com.worldnavigator.gameplay.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.worldnavigator.gameplay.fight.RockPaperScissor.*;

public class FightManager {
  private static final Map<String, FightBody> usersFightBody = new HashMap<>();
  private static final Map<String, String> playersInFight = new HashMap<>();

  public static void addUser(String user, FightBody fightBody) {
    usersFightBody.put(user, fightBody);
  }

  public static Map<String, FightBody> getUsersFightBody() {
    return usersFightBody;
  }

  public static void addPlayers(String player1, String player2) {
    playersInFight.put(player1, player2);
  }

  public static String getEnemy(String player) {
    if (playersInFight.get(player) != null) {
      System.out.println(playersInFight.get(player));
      return playersInFight.get(player);
    }

    return null;
  }

  public static boolean enemyChose(FightBody fightBody) {

    return fightBody.getChosen() != null;
  }

  public static FightBody getFightBody(String player) {
    return usersFightBody.get(player);
  }

  public static String fight(FightBody fightBody, FightBody fightBody2) {
    String currentPlayerPick = fightBody.getChosen();
    switch (currentPlayerPick) {
      case "rock":
        return caseRockResult(fightBody, fightBody2);

      case "paper":
        return casePaperResult(fightBody, fightBody2);

      case "scissor":
        return caseScissorResult(fightBody, fightBody2);
    }
    return "";
  }

  public static void cleanUsersInFight(String userName) {
    String enemy = FightManager.getEnemy(userName);
    usersFightBody.remove(userName);
    usersFightBody.remove(enemy);
    playersInFight.remove(userName);
    playersInFight.remove(enemy);
  }

  public static String startFight(String userName) {
    long elapsedTime = 0L;
    long startTime = System.currentTimeMillis();
    int requiredTimeInMillis = 60 * 1000; // 25 seconds
    FightBody fightBody = getFightBody(userName);
    FightBody fightBody2 = getFightBody(getEnemy(userName));
    Player player1 = EntitiesManager.getPlayer(fightBody.getUserName());
    Player player2 = EntitiesManager.getPlayer(fightBody2.getUserName());
    while (!enemyChose(fightBody2)) {
      if (elapsedTime > requiredTimeInMillis) {
        win(player1, player2);
        cleanUsersInFight(userName);
        return "won";
      }
      elapsedTime = (new Date()).getTime() - startTime;
    }
    return fight(fightBody, fightBody2);
  }
}
