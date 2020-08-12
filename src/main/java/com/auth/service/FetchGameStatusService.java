package com.auth.service;

import com.auth.model.ExecutionBody;
import com.auth.model.Fetch;
import com.auth.model.PlayerEntity;
import com.auth.model.PlayersCount;
import com.worldnavigator.configurations.JsonConverter;
import com.worldnavigator.configurations.MapBuilder;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.managers.GameManager;
import com.worldnavigator.managers.GameMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchGameStatusService {
  @Autowired private MapService mapRepository;
  @Autowired private PlayersCountService playersCountRepository;
  @Autowired private PlayerService playerEntityRepository;
  @Autowired private GameManager gameManager;
  @Autowired private GameMonitor gameMonitor;
  private final JsonConverter jsonConverter = new JsonConverter();

  public Fetch fetchGameStatus(ExecutionBody executionBody) {
    String userName = executionBody.getUserName();
    int mapId;
    String[] users;
    Fetch fetch = new Fetch();

    if (playerEntityRepository.findByUserName(userName) != null) {
      PlayerEntity playerEntity = playerEntityRepository.findByUserName(userName);
      Player player = jsonConverter.convertJsonToPlayer(playerEntity.getPlayer());
      if (player.getMapId() != -1) {
        mapId = player.getMapId();
        users = getUsers(mapId);
        fetch.setActiveUsers(users);
        fetch.setTie(player.isTie());
      } else {
        fetch.setActiveUsers(new String[0]);
      }
    } else {
      fetch.lost();
    }
    return fetch;
  }

  private String[] getUsers(int mapId) {
    if (playersCountRepository.findByMapId(mapId) != null) {
      return playersCountRepository
          .findByMapId(mapId)
          .getPlayers()
          .toArray(new String[getPlayersListSize(mapId)]);
    } else {
      return new String[0];
    }
  }

  private int getPlayersListSize(int mapId) {
    return playersCountRepository.findByMapId(mapId).getPlayers().size();
  }

  public void playerLoggedIn(String username) {
    if (mapRepository.findByMapId(-1) == null) {
      MapBuilder mapBuilder = new MapBuilder();
      mapRepository.save(mapBuilder.build(-1));
      PlayersCount playersCount = new PlayersCount();
      playersCount.setMapId(-1);
      playersCountRepository.save(playersCount);
    }
    Player player = new Player();
    player.setUserName(username);
    gameManager.addPlayer(player);
    gameMonitor.startGameAfter2Min();
  }
}
