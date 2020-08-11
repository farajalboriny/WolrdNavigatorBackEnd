package com.worldnavigator.archeticture.parts.impl;

import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.constants.PartType;
import com.worldnavigator.archeticture.parts.abstracts.Seller;

import java.util.List;

public class NormalSeller implements Seller {
  private List<Item> items;
  private PartType type;

  public NormalSeller() {}

  public NormalSeller(List<Item> items) {
    this.items = items;
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

  @Override
  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
