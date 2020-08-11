package com.worldnavigator.configurations;

import com.worldnavigator.archeticture.map.DefaultRoom;

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
