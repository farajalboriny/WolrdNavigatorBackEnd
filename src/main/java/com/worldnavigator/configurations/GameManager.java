package com.worldnavigator.configurations;

import com.auth.model.MapEntity;
import com.auth.model.PlayerEntity;
import com.auth.model.PlayersCount;
import com.auth.service.MapService;
import com.auth.service.PlayerService;
import com.auth.service.PlayersCountService;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameManager {
  private final JsonConverter jsonConverter = new JsonConverter();
  @Autowired private PlayerService playerService;
  @Autowired private MapService mapService;
  @Autowired private PlayersCountService playersCountService;
  private final int notActive = -1;

  public GameManager() {}

  public void addPlayer(Player player) {
    PlayerEntity playerEntity = new PlayerEntity();
    playerEntity.setPlayer(jsonConverter.convertPlayerToJson(player));
    playerEntity.setUserName(player.getUserName());
    playerService.save(playerEntity);
    PlayersCount playersCount = playersCountService.findByMapId(notActive);
    playersCount.addPlayer(player.getUserName());
    if (playersCount.getPlayers().size() == 2) {
      addMap(generateNewMapIdNumber());
    }
    playersCountService.save(playersCount);
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

  private final List<Integer> startingRooms = new ArrayList<>();

  public void addMap(int mapId) {

    PlayersCount playersCount = playersCountService.findByMapId(notActive);
    MapEntity mapEntity = mapService.findByMapId(notActive);
    List<String> jsonPlayers = playersCount.getPlayers();
    List<Player> players = new ArrayList<>();
    for (String userName : jsonPlayers) {
      PlayerEntity playerEntity = playerService.findByUserName(userName);
      String jsonPlayer = playerEntity.getPlayer();
      Player player = jsonConverter.convertJsonToPlayer(jsonPlayer);
      player.setMapId(mapId);
      player.setCurrentRoomId(generateNewRoomIdNumber());
      players.add(player);
      playerEntity.setPlayer(jsonConverter.convertPlayerToJson(player));
      playerService.save(playerEntity);
    }
    playersCount.setMapId(mapId);
    playersCountService.save(playersCount);
    mapEntity.setMapId(mapId);
    mapService.save(mapEntity);
    MapBuilder builder = new MapBuilder();
    builder.build(mapId);
    EntitiesGetter.addPlayersMapEntities(players);
    GameMonitor gameMonitor = new GameMonitor();
    gameMonitor.startTimer(mapId);
    startingRooms.clear();
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

  public void removeAllPlayers(int mapId) {
    List<String> playersList = playersCountService.findByMapId(mapId).getPlayers();
    for (String userName : playersList) {
      PlayerEntity playerEntity = playerService.findByUserName(userName);
      playerService.delete(playerEntity);
    }
    mapService.deleteByMapId(mapId);
  }
}
