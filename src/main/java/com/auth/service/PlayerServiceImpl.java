package com.auth.service;

import com.auth.model.PlayerEntity;
import com.auth.repository.PlayerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
  @Autowired private PlayerEntityRepository playerEntityRepository;

  @Override
  public PlayerEntity findByUserName(String userName) {
    return playerEntityRepository.findByUserName(userName);
  }

  @Override
  public void delete(PlayerEntity playerEntity) {
    playerEntityRepository.delete(playerEntity);
  }

  @Override
  public void deleteByUserName(String userName) {
    playerEntityRepository.deleteByUserName(userName);
  }

  @Override
  public void save(PlayerEntity playerEntity) {
    playerEntityRepository.save(playerEntity);
  }
}
