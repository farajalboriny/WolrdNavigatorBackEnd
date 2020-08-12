package com.worldnavigator.archeticture.constants;

import com.worldnavigator.archeticture.map.Part;
import com.worldnavigator.managers.EntitiesManager;
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

    return EntitiesManager.getRoom(EntitiesManager.getPlayer(userName)).getForwardParts();
  }

  private Part getBackwardParts(String userName) {
    return EntitiesManager.getRoom(EntitiesManager.getPlayer(userName)).getBackwardParts();
  }

  private Part getEastParts(String userName) {
    return EntitiesManager.getRoom(EntitiesManager.getPlayer(userName)).getEastParts();
  }

  private Part getWestParts(String userName) {
    return EntitiesManager.getRoom(EntitiesManager.getPlayer(userName)).getWestParts();
  }

  public Map<String, Part> getAvailablePartsMap() {
    return availablePartsMap;
  }
}
