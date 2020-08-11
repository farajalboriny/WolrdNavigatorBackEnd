package com.worldnavigator.archeticture.parts.abstracts;

import com.worldnavigator.archeticture.constants.Item;

public interface Mirror extends RoomPart {

  boolean hasKey();

  Item getKey();

  void removeKey();
}
