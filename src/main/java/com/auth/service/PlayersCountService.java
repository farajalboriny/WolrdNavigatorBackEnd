package com.auth.service;

import com.auth.model.PlayersCount;

public interface PlayersCountService {
    PlayersCount findByMapId(int mapId);
    void save(PlayersCount playersCount);
}
