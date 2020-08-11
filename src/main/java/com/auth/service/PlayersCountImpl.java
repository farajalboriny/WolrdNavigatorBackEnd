package com.auth.service;

import com.auth.model.PlayersCount;
import com.auth.repository.PlayersCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersCountImpl implements PlayersCountService {
  @Autowired private PlayersCountRepository playersCountRepository;

  @Override
  public PlayersCount findByMapId(int mapId) {
    return playersCountRepository.findByMapId(mapId);
  }

  @Override
  public void save(PlayersCount playersCount) {
    playersCountRepository.save(playersCount);
  }
}
