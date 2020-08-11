package com.worldnavigator.archeticture.constants;

import com.worldnavigator.archeticture.map.Part;
import com.worldnavigator.gameplay.EntitiesGetter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AvailablePartsMap {
  private Map<String, Part> availablePartsMap = new HashMap<>();

  public AvailablePartsMap(String userName) {
    availablePartsMap =
        new HashMap<>() {
          {
            put("Forward", getForwardParts(userName));
            put("Backward", getBackwardParts(userName));
            put("East", getEastParts(userName));
            put("West", getWestParts(userName));
          }
        };
  }

  public AvailablePartsMap() {}

  private Part getForwardParts(String userName) {

    return EntitiesGetter.getRoom(EntitiesGetter.getPlayer(userName)).getForwardParts();
  }

  private Part getBackwardParts(String userName) {
    return EntitiesGetter.getRoom(EntitiesGetter.getPlayer(userName)).getBackwardParts();
  }

  private Part getEastParts(String userName) {
    return EntitiesGetter.getRoom(EntitiesGetter.getPlayer(userName)).getEastParts();
  }

  private Part getWestParts(String userName) {
    return EntitiesGetter.getRoom(EntitiesGetter.getPlayer(userName)).getWestParts();
  }

  public Map<String, Part> getAvailablePartsMap() {
    return availablePartsMap;
  }
}
