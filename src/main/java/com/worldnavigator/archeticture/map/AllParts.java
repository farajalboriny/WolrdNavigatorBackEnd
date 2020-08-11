package com.worldnavigator.archeticture.map;

public class AllParts {
  private ForwardPart forwardParts;
  private BackwardPart backwardParts;
  private EastPart eastParts;
  private WestPart westParts;

  public AllParts(
      ForwardPart forwardParts,
      BackwardPart backwardParts,
      EastPart eastParts,
      WestPart westParts) {
    this.forwardParts = forwardParts;
    this.backwardParts = backwardParts;
    this.eastParts = eastParts;
    this.westParts = westParts;
  }

  public AllParts() {}

  public void setForwardParts(ForwardPart forwardParts) {
    this.forwardParts = forwardParts;
  }

  public void setBackwardParts(BackwardPart backwardParts) {
    this.backwardParts = backwardParts;
  }

  public void setEastParts(EastPart eastParts) {
    this.eastParts = eastParts;
  }

  public void setWestParts(WestPart westParts) {
    this.westParts = westParts;
  }

  public Part getForwardParts() {
    return forwardParts;
  }

  public Part getBackwardParts() {
    return backwardParts;
  }

  public Part getEastParts() {
    return eastParts;
  }

  public Part getWestParts() {
    return westParts;
  }
}
