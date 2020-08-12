package com.worldnavigator.configurations;

import com.auth.model.MapEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldnavigator.archeticture.map.GameMap;

import java.io.IOException;

public class MapBuilder {

  public MapBuilder() {}

  public MapEntity build(int mapId) {
    GameMap gameMap = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      gameMap = objectMapper.readValue(classLoader.getResource("map.json"), GameMap.class);

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
