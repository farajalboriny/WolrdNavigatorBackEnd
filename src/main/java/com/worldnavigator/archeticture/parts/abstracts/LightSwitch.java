package com.worldnavigator.archeticture.parts.abstracts;

import javax.naming.OperationNotSupportedException;

public interface LightSwitch {
  boolean hasSwitch();

  void flipSwitch() throws OperationNotSupportedException;
}
