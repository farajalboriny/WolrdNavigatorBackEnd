package com.worldnavigator.gameplay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.worldnavigator.archeticture.constants.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
  private int gold;
  private List<Item> list;

  public Inventory(int amount) {
    list = new ArrayList<>();
    gold = amount;
  }

  public Inventory() {}

  public int getGold() {
    return gold;
  }

  public void addItem(Item item) {
    list.add(item);
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public void removeItem(Item item) {
    list.remove(item);
  }


  public List<Item> getList() {
    return list;
  }

  public int calculateValue() {
    int value = 0;
    List<Item> list = this.list;
    for (Item item : list) {
      value += item.getPrice();
    }
    return value + getGold();
  }

  public void removeItems(List<Item> items) {
    this.getItems().removeAll(items);
  }

  public void addGold(int gold) {
    this.setGold(this.getGold() + gold);
  }

  public void subtractGold(int gold) {
    this.setGold(this.getGold() - gold);
  }

  public boolean hasItem(Item item) {
    return list.contains(item);
  }
  @JsonIgnore
  public List<Item> getItems() {
    return list;
  }

  public void addItems(List<Item> list) {

    this.list.addAll(list);
  }

  public void setList(List<Item> list) {
    this.list = list;
  }
}
