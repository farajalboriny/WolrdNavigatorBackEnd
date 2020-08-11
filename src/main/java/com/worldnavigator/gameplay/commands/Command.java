package com.worldnavigator.gameplay.commands;

import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import javax.naming.OperationNotSupportedException;

public interface Command {
  void execute(Player player) throws IllegalCommandException, OperationNotSupportedException;
}
