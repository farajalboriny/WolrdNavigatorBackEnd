package com.worldnavigator.archeticture.parts.impl;

import com.worldnavigator.archeticture.constants.PartType;
import com.worldnavigator.archeticture.parts.abstracts.Door;

public class NormalDoor implements Door {
  private boolean winningDoor;
  private boolean open;
  private PartType type;

  public NormalDoor(boolean winningDoor, boolean isOpen) {
    this.winningDoor = winningDoor;
    this.open = isOpen;
  }

  public NormalDoor() {}

  public boolean isWinningDoor() {
    return winningDoor;
  }

  public void setWinningDoor(boolean winningDoor) {
    this.winningDoor = winningDoor;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  @Override
  public PartType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return getType().toString();
  }

  public void setType(PartType type) {
    this.type = type;
  }
}
