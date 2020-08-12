package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public class EastPart implements Part {
  private List<RoomPart> eastParts;

  public EastPart(List<RoomPart> eastParts) {
    this.eastParts = eastParts;
  }

  public EastPart() {}

  public void setEastParts(List<RoomPart> eastParts) {
    this.eastParts = eastParts;
  }

  public List<RoomPart> getEastParts() {
    return eastParts;
  }

  @Override
  public Part getLeft(Player player) {
    return getCurrentRoom(player).getBackwardParts();
  }

  @Override
  public Part getRight(Player player) {
    return getCurrentRoom(player).getForwardParts();
  }

  @Override
  public List<RoomPart> getBackwardParts(Player player) {
    return getCurrentRoom(player).getWestParts().getParts();
  }

  @Override
  public List<RoomPart> getParts() {
    return eastParts;
  }

  @Override
  public boolean IsNextDoor(Player player) {
    return getCurrentRoom(player).getLeft() != -1;
  }

  @Override
  public boolean IsPreviousDoor(Player player) {
    return getCurrentRoom(player).getRight() != -1;
  }

  @Override
  public int getNext(Player player) {
    return getCurrentRoom(player).getLeft();
  }

  @Override
  public int getPrevious(Player player) {
    return getCurrentRoom(player).getLeft();
  }

  @Override
  public String toString() {
    return "east";
  }

  private DefaultRoom getCurrentRoom(Player player) {

    return EntitiesManager.getRoom(player);
  }
}
