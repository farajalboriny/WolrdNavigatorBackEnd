package com.worldnavigator.configurations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.worldnavigator.archeticture.map.GameMap;
import com.worldnavigator.gameplay.Player;

import java.io.IOException;

public class JsonConverter {
  public GameMap convertJsonToMap(String jsonMap) {

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(jsonMap, GameMap.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String convertMapToJson(GameMap gameMap) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(gameMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Player convertJsonToPlayer(String jsonPlayer) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    try {
      return objectMapper.readValue(jsonPlayer, Player.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String convertPlayerToJson(Player jsonPlayer) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    try {
      return objectMapper.writeValueAsString(jsonPlayer);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
