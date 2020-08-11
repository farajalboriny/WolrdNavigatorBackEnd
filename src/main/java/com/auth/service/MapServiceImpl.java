package com.auth.service;

import com.auth.model.MapEntity;
import com.auth.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
  @Autowired private MapRepository mapRepository;

  @Override
  public MapEntity findByMapId(int mapId) {
    return mapRepository.findByMapId(mapId);
  }

  @Override
  public void save(MapEntity mapEntity) {
   mapRepository.save(mapEntity);
  }

  @Override
  public void deleteByMapId(int mapId) {
    mapRepository.deleteByMapId(mapId);
  }
}
