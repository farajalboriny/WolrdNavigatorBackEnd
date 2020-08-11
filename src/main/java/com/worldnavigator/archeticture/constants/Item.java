package com.worldnavigator.archeticture.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Item {
  @JsonProperty("flashlight")
  flashlight(2),
  @JsonProperty("doorkey")
  doorkey(10),
  @JsonProperty("CHEST_KEY")
  CHEST_KEY(10),
  @JsonProperty("winningdoorkey")
  winningdoorkey(10);
  private int price;

  Item(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return name();
  }
}
