package com.worldnavigator.managers;

import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.archeticture.parts.impl.NormalSeller;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class NonPlayerCharManager {

  public NonPlayerCharManager() {}

  public static NormalSeller getSeller(Player player) throws IllegalCommandException {
    List<RoomPart> parts = PartsManager.getAvailableParts(player);
    NormalSeller seller = null;
    for (RoomPart part : parts) {
      if (part instanceof NormalSeller) {
        seller = (NormalSeller) part;
      }
    }
    if (seller == null) {
      throw new IllegalCommandException("there is no seller here");
    }
    return seller;
  }

  public static void tradeModeCheck(Player player) throws IllegalCommandException {
    if (player.checkTradeMode()) {
      throw new IllegalCommandException(
          "its not polite to leave someone before saying good by please say"
              + "good bye to the trader by using finish trade command");
    }
  }
}
