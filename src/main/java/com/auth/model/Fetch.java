package com.auth.model;

public class Fetch {
  private String[] activeUsers;
  private boolean lost = false;
  private boolean tie = false;

  public String[] getActiveUsers() {
    return activeUsers;
  }

  public void setActiveUsers(String[] activeUsers) {
    this.activeUsers = activeUsers;
  }

  public boolean isTie() {
    return tie;
  }

  public void setTie(boolean tie) {
    this.tie = tie;
  }

  public boolean isLost() {
    return lost;
  }

  public void lost() {
    this.lost = !this.lost;
  }
}
