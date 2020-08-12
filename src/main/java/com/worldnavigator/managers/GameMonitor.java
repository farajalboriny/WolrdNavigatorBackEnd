package com.worldnavigator.managers;

import com.auth.model.MapEntity;
import com.auth.model.PlayerEntity;
import com.auth.model.PlayersCount;
import com.auth.service.MapService;
import com.auth.service.PlayerService;
import com.auth.service.PlayersCountService;
import com.worldnavigator.configurations.JsonConverter;
import com.worldnavigator.configurations.MapBuilder;
import com.worldnavigator.gameplay.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameMonitor {
  @Autowired private PlayerService playerService;
  @Autowired private MapService mapService;
  @Autowired private PlayersCountService playersCountService;
  private final JsonConverter jsonConverter = new JsonConverter();

  public void startTimer(int mapId) {
    GameManager gameManager = new GameManager();
    Thread thread =
        new Thread("Thread1") {
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

  private Thread thread = new Thread();

  public void startGameAfter2Min() {
    if (!thread.isAlive()) {
      thread =
          new Thread("Thread2") {
            @Override
            public void run() {
              int notActive = -1;
              PlayersCount playersCount = playersCountService.findByMapId(notActive);
              if (playersCount != null) {
                long elapsedTime = 0L;
                long startTime = System.currentTimeMillis();
                int requiredTimeInMillis = 30 * 1000; // 30 sec
                while (elapsedTime < requiredTimeInMillis) {
                  elapsedTime = (new Date()).getTime() - startTime;
                }
                addMap(generateNewMapIdNumber());
              }
            }
          };
      thread.start();
    }
  }

  private int generateNewMapIdNumber() {
    int min = 0;
    int max = 1000;
    int range = max - min + 1;
    int random = (int) (Math.random() * range) + min;
    while (mapService.findByMapId(random) != null) {
      random = (int) (Math.random() * range) + min;
    }
    return random;
  }

  public void addMap(int mapId) {
    int notActive = -1;
    PlayersCount playersCount = playersCountService.findByMapId(notActive);
    MapEntity mapEntity = mapService.findByMapId(notActive);
    List<String> jsonPlayers = playersCount.getPlayers();
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < jsonPlayers.size(); i++) {
      String userName = jsonPlayers.get(i);
      addPlayerToTheDataBase(mapId, players, userName);
    }
    startGame(mapId, playersCount, mapEntity, players);
  }

  private final List<Integer> startingRooms = new ArrayList<>();

  private void startGame(
      int mapId, PlayersCount playersCount, MapEntity mapEntity, List<Player> players) {
    playersCount.setMapId(mapId);
    playersCountService.save(playersCount);
    mapEntity.setMapId(mapId);
    mapService.save(mapEntity);
    MapBuilder builder = new MapBuilder();
    builder.build(mapId);
    EntitiesManager.addPlayersMapEntities(players);
    GameMonitor gameMonitor = new GameMonitor();
    gameMonitor.startTimer(mapId);
    startingRooms.clear();
  }

  private void addPlayerToTheDataBase(int mapId, List<Player> players, String userName) {
    PlayerEntity playerEntity = playerService.findByUserName(userName);
    String jsonPlayer = playerEntity.getPlayer();
    Player player = jsonConverter.convertJsonToPlayer(jsonPlayer);
    player.setMapId(mapId);
    player.setCurrentRoomId(generateNewRoomIdNumber());
    players.add(player);
    playerEntity.setPlayer(jsonConverter.convertPlayerToJson(player));
    playerService.save(playerEntity);
  }

  private int generateNewRoomIdNumber() {
    int min = 0;
    int max = 49;
    int range = max - min + 1;
    int random = (int) (Math.random() * range) + min;
    while (startingRooms.contains(random)) {
      random = (int) (Math.random() * range) + min;
    }
    startingRooms.add(random);
    return random;
  }
}
