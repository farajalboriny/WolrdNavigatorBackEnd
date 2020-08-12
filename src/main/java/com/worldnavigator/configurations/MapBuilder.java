package com.worldnavigator.configurations;

import com.auth.model.MapEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MapBuilder {

  public MapBuilder() {}

  public MapEntity build(int mapId) {
    GameMap gameMap = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      gameMap = objectMapper.readValue(readFile(), GameMap.class);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    MapEntity mapEntity = new MapEntity();
    mapEntity.setMapId(mapId);
    JsonConverter jsonConverter = new JsonConverter();
    mapEntity.setMap(jsonConverter.convertMapToJson(gameMap));
    return mapEntity;
  }

  private String readFile() throws IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream("map.json");
    if (stream != null) {
      BufferedInputStream buffer = new BufferedInputStream(stream);
      StringBuilder builder = new StringBuilder();
      int read;
      while ((read = buffer.read()) != -1) {
        builder.append((char) read);
      }
      return builder.toString();
    }
    throw new IOException("error while reading file");
  }
}
