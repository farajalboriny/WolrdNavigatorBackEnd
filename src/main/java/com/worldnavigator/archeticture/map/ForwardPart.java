package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public class ForwardPart implements Part {
  private List<RoomPart> forwardParts;

  public ForwardPart(List<RoomPart> forwardParts) {

    this.forwardParts = forwardParts;
  }

  public ForwardPart() {}

  public void setForwardParts(List<RoomPart> forwardParts) {
    this.forwardParts = forwardParts;
  }

  public List<RoomPart> getForwardParts() {
    return forwardParts;
  }

  @Override
  public Part getLeft(Player player) {
    return getCurrentRoom(player).getEastParts();
  }

  @Override
  public Part getRight(Player player) {
    return getCurrentRoom(player).getWestParts();
  }

  @Override
  public List<RoomPart> getBackwardParts(Player player) {
    return getCurrentRoom(player).getBackwardParts().getParts();
  }

  @Override
  public String toString() {
    return "Forward";
  }

  @Override
  public boolean IsNextDoor(Player player) {
    return getCurrentRoom(player).getNext() != -1;
  }

  @Override
  public boolean IsPreviousDoor(Player player) {
    return getCurrentRoom(player).getPrevious() != -1;
  }

  @Override
  public int getNext(Player player) {
    return getCurrentRoom(player).getNext();
  }

  @Override
  public int getPrevious(Player player) {
    return getCurrentRoom(player).getPrevious();
  }

  public List<RoomPart> getParts() {
    return forwardParts;
  }

  private DefaultRoom getCurrentRoom(Player player) {

    return EntitiesGetter.getRoom(player);
  }
}
