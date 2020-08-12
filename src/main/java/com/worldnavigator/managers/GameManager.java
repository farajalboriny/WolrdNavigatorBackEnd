package com.worldnavigator.managers;

import com.auth.model.PlayerEntity;
import com.auth.model.PlayersCount;
import com.auth.service.MapService;
import com.auth.service.PlayerService;
import com.auth.service.PlayersCountService;
import com.worldnavigator.configurations.JsonConverter;
import com.worldnavigator.gameplay.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameManager {
  private final JsonConverter jsonConverter = new JsonConverter();
  @Autowired private PlayerService playerService;
  @Autowired private MapService mapService;
  @Autowired private PlayersCountService playersCountService;

  public GameManager() {}

  public void addPlayer(Player player) {
    PlayerEntity playerEntity = new PlayerEntity();
    playerEntity.setPlayer(jsonConverter.convertPlayerToJson(player));
    playerEntity.setUserName(player.getUserName());
    playerService.save(playerEntity);
    int notActive = -1;
    PlayersCount playersCount = playersCountService.findByMapId(notActive);
    playersCount.addPlayer(player.getUserName());
    playersCountService.save(playersCount);
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
