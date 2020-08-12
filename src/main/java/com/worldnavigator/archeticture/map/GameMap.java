package com.worldnavigator.archeticture.map;

import java.util.List;

public class GameMap {
  private List<DefaultRoom> rooms;

  public GameMap() {}

  public List<DefaultRoom> getRooms() {
    return rooms;
  }

  public void setRooms(List<DefaultRoom> rooms) {
    this.rooms = rooms;
  }
}
