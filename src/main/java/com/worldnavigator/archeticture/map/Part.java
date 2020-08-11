package com.worldnavigator.archeticture.map;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.gameplay.Player;

import java.util.List;

public interface Part {
  Part getLeft(Player player);

  Part getRight(Player player);

  List<RoomPart> getBackwardParts(Player player);

  List<RoomPart> getParts();

  int getNext(Player player);

  int getPrevious(Player player);

  boolean IsNextDoor(Player player);

  boolean IsPreviousDoor(Player player);
}
