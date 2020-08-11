package com.worldnavigator.gameplay.exceptions;

import com.worldnavigator.gameplay.ConsolePrinter;

public class IllegalCommandException extends Exception {
  public IllegalCommandException(String message) {
    super(message);
    ConsolePrinter.result=message;
  }
}
