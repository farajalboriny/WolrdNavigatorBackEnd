package com.worldnavigator.configurations;

import com.auth.model.MapEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MapBuilder {

  public MapBuilder() {}

  public MapEntity build(int mapId) {
    GameMap gameMap = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      gameMap = objectMapper.readValue(new File("codebeautify2.json"), GameMap.class);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    MapEntity mapEntity = new MapEntity();
    mapEntity.setMapId(mapId);
    JsonConverter jsonConverter = new JsonConverter();
    mapEntity.setMap(jsonConverter.convertMapToJson(gameMap));
    return mapEntity;
  }
}
