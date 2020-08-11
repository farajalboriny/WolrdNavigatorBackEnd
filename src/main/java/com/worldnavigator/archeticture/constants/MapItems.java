package com.worldnavigator.archeticture.constants;

import java.util.HashMap;
import java.util.Map;

public class MapItems {
  private static final Map<String, Item> itemsMap =
      new HashMap<>() {
        {
          put("flashlight", Item.flashlight);
          put("doorkey", Item.doorkey);
          put("winningdoorkey", Item.winningdoorkey);
          put("CHEST_KEY", Item.CHEST_KEY);
        }
      };

  public static Map<String, Item> getMapItems() {
    return itemsMap;
  }
}
