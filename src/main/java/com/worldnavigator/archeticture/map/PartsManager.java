package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.constants.AvailablePartsMap;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public class PartsManager {

  public static List<RoomPart> getAvailableParts(Player player) {
    String orientation = player.getOrientations();
    AvailablePartsMap availablePartsMap = new AvailablePartsMap(player.getUserName());
    return availablePartsMap.getAvailablePartsMap().get(orientation).getParts();
  }

  public static Part getParts(Player player) {
    String orientation = player.getOrientations();
    AvailablePartsMap availablePartsMap = new AvailablePartsMap(player.getUserName());
    return availablePartsMap.getAvailablePartsMap().get(orientation);
  }
}
