package com.auth.repository;

import com.auth.model.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<MapEntity, Long> {
  MapEntity findByMapId(int mapId);

  void deleteByMapId(int mapId);
}
