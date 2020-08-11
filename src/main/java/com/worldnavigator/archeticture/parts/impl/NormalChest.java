package com.worldnavigator.archeticture.parts.impl;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.constants.PartType;
import com.worldnavigator.archeticture.parts.abstracts.Chest;

import java.util.List;

public class NormalChest implements Chest {
  private List<Item> items;
  private boolean needsKey;
  private int gold;
  private PartType type;

  public NormalChest() {}

  public NormalChest(List<Item> items, boolean needsKey, int gold) {
    this.items = items;
    this.needsKey = needsKey;
    this.gold = gold;
  }

  @Override
  public int getGold() {
    return gold;
  }

  @Override
  public boolean needsKey() {
    return needsKey;
  }

  @Override
  public void openChest() {
    this.needsKey = false;
  }

  @Override
  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public boolean isNeedsKey() {
    return needsKey;
  }

  public void setNeedsKey(boolean needsKey) {
    this.needsKey = needsKey;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  @Override
  public PartType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return getType().toString();
  }

  public void setType(PartType type) {
    this.type = type;
  }
}
