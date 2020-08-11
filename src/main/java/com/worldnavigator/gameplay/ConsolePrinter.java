package com.worldnavigator.gameplay;

import com.worldnavigator.archeticture.constants.Item;

import java.util.Collection;

public class ConsolePrinter implements Printer {
  public static String result;

  @Override
  public void print(String text) {
    result = text;
  }

  @Override
  public void print(Collection<Item> list) {
    result = list.toString();
  }
}
