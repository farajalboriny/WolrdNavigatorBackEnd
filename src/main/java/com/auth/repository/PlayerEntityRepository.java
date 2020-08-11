package com.auth.repository;

import com.auth.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, Long> {
  PlayerEntity findByUserName(String userName);
  void deleteByUserName(String userName);
}
