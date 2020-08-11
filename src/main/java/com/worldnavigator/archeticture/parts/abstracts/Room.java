package com.worldnavigator.archeticture.parts.abstracts;

import javax.naming.OperationNotSupportedException;

public interface Room {
  boolean isLit();

  void flipLit();

  boolean hasSwitch();

  void turnSwitch() throws OperationNotSupportedException;
}
