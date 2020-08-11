package com.worldnavigator.archeticture.constants;

import java.util.HashMap;
import java.util.Map;

public class OrientationsMap {
  private static final Map<String, String> leftRotateMap =
      new HashMap<>() {
        {
          put("Forward", "East");
          put("Backward", "West");
          put("East", "Backward");
          put("West", "Forward");
        }
      };
  private static final Map<String, String> rightRotateMap =
      new HashMap<>() {
        {
          put("Forward", "West");
          put("Backward", "East");
          put("East", "Forward");
          put("West", "Backward");
        }
      };

  public static Map<String, String> getLeftRotateMap() {
    return leftRotateMap;
  }

  public static Map<String, String> getRightRotateMap() {
    return rightRotateMap;
  }
}
