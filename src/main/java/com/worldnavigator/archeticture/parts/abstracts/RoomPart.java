package com.worldnavigator.archeticture.parts.abstracts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.worldnavigator.archeticture.constants.PartType;
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public interface RoomPart {
  PartType getType();
}
