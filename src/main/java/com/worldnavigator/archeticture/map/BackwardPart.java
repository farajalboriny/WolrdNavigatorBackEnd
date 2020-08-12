package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.managers.EntitiesManager;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public class BackwardPart implements Part {
  private List<RoomPart> backwardParts;

  public BackwardPart(List<RoomPart> backwardParts) {
    this.backwardParts = backwardParts;
  }

  public BackwardPart() {}

  public void setBackwardParts(List<RoomPart> backwardParts) {
    this.backwardParts = backwardParts;
  }

  public List<RoomPart> getBackwardParts() {
    return backwardParts;
  }

  @Override
  public Part getLeft(Player player) {
    return getCurrentRoom(player).getWestParts();
  }

  @Override
  public Part getRight(Player player) {
    return getCurrentRoom(player).getEastParts();
  }

  @Override
  public List<RoomPart> getBackwardParts(Player player) {
    return getCurrentRoom(player).getForwardParts().getParts();
  }

  @Override
  public List<RoomPart> getParts() {
    return backwardParts;
  }

  @Override
  public boolean IsNextDoor(Player player) {
    return getCurrentRoom(player).getPrevious() != -1;
  }

  @Override
  public int getNext(Player player) {
    return getCurrentRoom(player).getPrevious();
  }

  @Override
  public int getPrevious(Player player) {
    return getCurrentRoom(player).getNext();
  }

  @Override
  public boolean IsPreviousDoor(Player player) {
    return getCurrentRoom(player).getNext() != -1;
  }

  @Override
  public String toString() {
    return "backward";
  }

  private DefaultRoom getCurrentRoom(Player player) {
    return EntitiesManager.getRoom(player);
  }
}
