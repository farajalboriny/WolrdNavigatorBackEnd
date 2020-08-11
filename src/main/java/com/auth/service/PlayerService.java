package com.auth.service;

import com.auth.model.PlayerEntity;

public interface PlayerService {
  PlayerEntity findByUserName(String userName);
  void delete(PlayerEntity playerEntity);
  void deleteByUserName(String userName);
  void save(PlayerEntity playerEntity);
}
