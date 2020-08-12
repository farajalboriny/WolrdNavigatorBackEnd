package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public class WestPart implements Part {
  private List<RoomPart> westParts;

  public WestPart(List<RoomPart> westParts) {
    this.westParts = westParts;
  }

  public WestPart() {}

  public void setWestParts(List<RoomPart> westParts) {
    this.westParts = westParts;
  }

  public List<RoomPart> getWestParts() {
    return westParts;
  }

  @Override
  public Part getLeft(Player player) {
    return getCurrentRoom(player).getForwardParts();
  }

  @Override
  public Part getRight(Player player) {
    return getCurrentRoom(player).getBackwardParts();
  }

  @Override
  public List<RoomPart> getBackwardParts(Player player) {
    return getCurrentRoom(player).getEastParts().getParts();
  }

  @Override
  public List<RoomPart> getParts() {
    return westParts;
  }

  @Override
  public int getNext(Player player) {
    return getCurrentRoom(player).getRight();
  }

  @Override
  public int getPrevious(Player player) {
    return getCurrentRoom(player).getLeft();
  }

  @Override
  public boolean IsNextDoor(Player player) {
    return getCurrentRoom(player).getRight() != -1;
  }

  @Override
  public boolean IsPreviousDoor(Player player) {
    return getCurrentRoom(player).getLeft() != -1;
  }

  @Override
  public String toString() {
    return "west";
  }

  private DefaultRoom getCurrentRoom(Player player) {

    return EntitiesManager.getRoom(player);
  }
}
