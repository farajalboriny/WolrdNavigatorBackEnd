package com.auth.repository;

import com.auth.model.PlayersCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersCountRepository extends JpaRepository<PlayersCount, Long> {
  PlayersCount findByMapId(int mapId);
}
