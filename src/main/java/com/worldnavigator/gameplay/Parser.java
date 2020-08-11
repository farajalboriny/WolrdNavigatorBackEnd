package com.worldnavigator.gameplay;

import com.worldnavigator.gameplay.commands.Command;
import com.worldnavigator.gameplay.exceptions.WrongCommandException;

public interface Parser {
  Command parse(String command) throws WrongCommandException;
}
