package com.auth.service;

import com.auth.model.MapEntity;

public interface MapService {
  MapEntity findByMapId(int mapId);
  void save(MapEntity mapEntity);
  void deleteByMapId(int mapId);
}
