package com.worldnavigator.gameplay.exceptions;

import com.worldnavigator.gameplay.ConsolePrinter;

public class WrongCommandException extends Exception {
  public WrongCommandException(String message) {
    super(message);
    ConsolePrinter.result=message;
  }
}
