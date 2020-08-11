package com.worldnavigator.archeticture.parts.abstracts;

import com.worldnavigator.archeticture.constants.Item;

import java.util.List;

public interface Chest extends RoomPart {
  int getGold();

  boolean needsKey();

  void openChest();

  List<Item> getItems();
}
