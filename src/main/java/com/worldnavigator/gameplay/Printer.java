package com.worldnavigator.gameplay;

import com.worldnavigator.archeticture.constants.Item;

import java.util.Collection;

public interface Printer {
  void print(String text);

  void print(Collection<Item> list);
}
