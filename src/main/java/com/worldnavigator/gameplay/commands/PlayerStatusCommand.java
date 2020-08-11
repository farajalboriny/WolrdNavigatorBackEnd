package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;

import java.util.List;

public class PlayerStatusCommand implements Command {
  private final Printer printer;

  public PlayerStatusCommand(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void execute(Player player) {

    List<Item> list = player.getInventory().getItems();
    printer.print(
        list + " " + player.getInventory().getGold() + " Gold  " + player.getOrientations());
  }
}
