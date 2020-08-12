package com.worldnavigator.managers;

import com.auth.model.MapEntity;
import com.auth.model.PlayerEntity;
import com.auth.model.PlayersCount;
import com.auth.service.MapService;
import com.auth.service.PlayerService;
import com.auth.service.PlayersCountService;
import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.GameMap;
import com.worldnavigator.configurations.JsonConverter;
import com.worldnavigator.gameplay.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntitiesManager {
  private static final JsonConverter jsonConverter = new JsonConverter();
  private static final Map<String, MapEntity> entitiesMap = new HashMap<>();
  private static final Map<Integer, GameMap> gameMaps = new HashMap<>();
  private static MapService mapService;
  private static PlayerService playerService;
  private static PlayersCountService playersCountService;

  public static Player getPlayer(String userName) {
    PlayerEntity playerEntity = playerService.findByUserName(userName);
    return jsonConverter.convertJsonToPlayer(playerEntity.getPlayer());
  }

  public static DefaultRoom getRoom(Player player) {
    if (gameMaps.get(player.getMapId()) == null) {
      MapEntity mapEntity = entitiesMap.get(player.getUserName());
      GameMap map = jsonConverter.convertJsonToMap(mapEntity.getMap());
      gameMaps.put(player.getMapId(), map);
    }
    GameMap map = gameMaps.get(player.getMapId());
    return map.getRooms().get(player.getCurrentRoomId());
  }

  public static DefaultRoom getPreviousOrNextRoom(int roomId, Player player) {
    GameMap map = gameMaps.get(player.getMapId());
    return map.getRooms().get(roomId);
  }

  public static void addPlayersMapEntities(List<Player> playersList) {
    for (Player player : playersList) {
      MapEntity mapEntity = mapService.findByMapId(player.getMapId());
      GameMap map = jsonConverter.convertJsonToMap(mapEntity.getMap());
      entitiesMap.put(player.getUserName(), mapEntity);
      gameMaps.put(player.getMapId(), map);
    }
  }

  public static void save(Player player) {
    MapEntity mapEntity = entitiesMap.get(player.getUserName());
    GameMap map = gameMaps.get(player.getMapId());
    mapEntity.setMap(jsonConverter.convertMapToJson(map));
    mapService.save(mapEntity);
    PlayerEntity playerEntity = playerService.findByUserName(player.getUserName());
    playerEntity.setPlayer(jsonConverter.convertPlayerToJson(player));
    playerService.save(playerEntity);
  }

  public static void removePlayer(Player player) {
    PlayerEntity playerEntity = playerService.findByUserName(player.getUserName());
    playerService.delete(playerEntity);
    PlayersCount playersCount = playersCountService.findByMapId(player.getMapId());
    playersCount.removePlayer(player.getUserName());
    playersCountService.save(playersCount);
  }

  @Autowired
  public void setMapService(MapService mapService) {
    EntitiesManager.mapService = mapService;
  }

  @Autowired
  public void setPlayerService(PlayerService playerService) {
    EntitiesManager.playerService = playerService;
  }

  @Autowired
  public void setPlayersCountService(PlayersCountService playersCountService) {
    EntitiesManager.playersCountService = playersCountService;
  }

  public static List<String> findPlayers(int mapId) {
    return playersCountService.findByMapId(mapId).getPlayers();
  }
}
