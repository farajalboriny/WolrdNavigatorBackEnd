package com.worldnavigator.archeticture.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.worldnavigator.archeticture.constants.Item;
import com.worldnavigator.archeticture.parts.abstracts.Room;
import com.worldnavigator.archeticture.parts.impl.RoomSwitch;

import java.util.ArrayList;
import java.util.List;

public class DefaultRoom implements Room {
  private RoomSwitch roomSwitch;
  private boolean lit;
  private AllParts parts;
  private int next;
  private int previous;
  private int left;
  private int right;
  private List<Item> floorItems = new ArrayList<>();
  private int floorGold = 0;
  private int id;

  public DefaultRoom() {}

  public DefaultRoom(RoomSwitch roomSwitch, boolean lit, AllParts allParts) {
    this.roomSwitch = roomSwitch;
    this.lit = lit;
    this.parts = allParts;
  }

  public List<Item> getFloorItems() {
    return floorItems;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFloorItems(List<Item> floorItems) {
    this.floorItems = floorItems;
  }

  public int getFloorGold() {
    return floorGold;
  }

  public void setFloorGold(int floorGold) {
    this.floorGold = floorGold;
  }

  @JsonIgnore
  public Part getForwardParts() {
    return parts.getForwardParts();
  }

  @JsonIgnore
  public Part getBackwardParts() {
    return parts.getBackwardParts();
  }

  @JsonIgnore
  public Part getEastParts() {
    return parts.getEastParts();
  }

  @JsonIgnore
  public Part getWestParts() {
    return parts.getWestParts();
  }

  @Override
  public boolean isLit() {
    return lit;
  }

  @Override
  public void flipLit() {
    lit = !lit;
  }

  @Override
  public boolean hasSwitch() {
    return roomSwitch.hasSwitch();
  }

  @Override
  public void turnSwitch() {
    this.roomSwitch.flipSwitch();
    flipLit();
  }

  public AllParts getParts() {
    return parts;
  }

  public void setParts(AllParts parts) {
    this.parts = parts;
  }

  public int getNext() {
    return next;
  }

  public void setNext(int next) {
    this.next = next;
  }

  public int getPrevious() {
    return previous;
  }

  public void setPrevious(int previous) {
    this.previous = previous;
  }

  public int getLeft() {
    return left;
  }

  public void setLeft(int left) {
    this.left = left;
  }

  public int getRight() {
    return right;
  }

  public void setRight(int right) {
    this.right = right;
  }

  public RoomSwitch getRoomSwitch() {
    return roomSwitch;
  }

  public void setRoomSwitch(RoomSwitch roomSwitch) {
    this.roomSwitch = roomSwitch;
  }

  public void setLit(boolean lit) {
    this.lit = lit;
  }
}
