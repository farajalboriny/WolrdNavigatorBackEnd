package com.worldnavigator.configurations;

import java.util.Date;

public class GameMonitor {

  public void startTimer(int mapId) {
    GameManager gameManager = new GameManager();
    Thread thread =
        new Thread("New Thread") {
          public void run() {
            long elapsedTime = 0L;
            long startTime = System.currentTimeMillis();
            int requiredTimeInMillis = 30 * 60 * 1000; // 35 min
            while (elapsedTime < requiredTimeInMillis) {
              elapsedTime = (new Date()).getTime() - startTime;
            }
            try {
              gameManager.removeAllPlayers(mapId);
            } catch (NullPointerException e) {
              // someone won
            }
          }
        };
    thread.start();
  }
}
