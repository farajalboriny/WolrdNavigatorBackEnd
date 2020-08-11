package com.worldnavigator.archeticture.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PartType {
  @JsonProperty("EMPTY_WALL")
  EMPTY_WALL,
  @JsonProperty("SELLER")
  SELLER,
  @JsonProperty("PAINTING")
  PAINTING,
  @JsonProperty("CHEST")
  CHEST,
  @JsonProperty("DOOR")
  DOOR,
  @JsonProperty("MIRROR")
  MIRROR;
}
