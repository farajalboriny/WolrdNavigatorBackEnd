package com.worldnavigator.gameplay;

import com.worldnavigator.archeticture.constants.OrientationsMap;

public class Player {
  private Inventory inventory = new Inventory(10);
  private String orientations = "Forward";
  private int currentRoomId;
  private String userName;
  private int mapId = -1;
  private boolean tradeMode = false;
  private boolean tie = false;

  public Player(String userName) {
    this.userName = userName;
  }

  public Player() {}

  public boolean isTie() {
    return tie;
  }

  public void setTie(boolean tie) {
    this.tie = tie;
  }

  public boolean checkTradeMode() {
    return tradeMode;
  }

  public void switchTrade() {
    this.tradeMode = !tradeMode;
  }

  public String getUserName() {
    return userName;
  }

  public int getMapId() {
    return mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public int getCurrentRoomId() {
    return currentRoomId;
  }

  public void setCurrentRoomId(int currentRoomId) {
    this.currentRoomId = currentRoomId;
  }

  public String getOrientations() {
    return orientations;
  }

  public void leftRotate() {
    this.orientations = OrientationsMap.getLeftRotateMap().get(orientations);
  }

  public void rightRotate() {
    this.orientations = OrientationsMap.getRightRotateMap().get(orientations);
  }

  public void resetOrientations() {
    this.orientations = "Forward";
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public void setOrientations(String orientations) {
    this.orientations = orientations;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean isTradeMode() {
    return tradeMode;
  }

  public void setTradeMode(boolean tradeMode) {
    this.tradeMode = tradeMode;
  }
}
