package com.worldnavigator.archeticture.parts.impl;

import com.worldnavigator.archeticture.constants.PartType;
import com.worldnavigator.archeticture.parts.abstracts.Wall;

public class EmptyWall implements Wall {
  private PartType type;

  public EmptyWall() {}

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
