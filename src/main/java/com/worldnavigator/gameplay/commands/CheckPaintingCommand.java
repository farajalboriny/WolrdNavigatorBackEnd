package com.worldnavigator.gameplay.commands;

import com.worldnavigator.archeticture.map.DefaultRoom;
import com.worldnavigator.archeticture.map.PartsManager;
import com.worldnavigator.archeticture.parts.abstracts.Painting;
import com.worldnavigator.archeticture.parts.abstracts.RoomPart;
import com.worldnavigator.configurations.GameManager;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.NonPlayerCharManager;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.Printer;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;

import java.util.List;

public class CheckPaintingCommand implements Command {
    private final Printer printer;

    public CheckPaintingCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(Player player) throws IllegalCommandException {
        NonPlayerCharManager.tradeModeCheck(player);
        DefaultRoom defaultRoom = EntitiesGetter.getRoom(player);
        if (defaultRoom.isLit()) {
            Painting painting = getMirror(player);
            if (painting.hasKey()) {
                player.getInventory().addItem(painting.getKey());
                printer.print("added  " + painting.getKey().toString() + " to the inventory");
                painting.removeKey();
                EntitiesGetter.save(player);
            } else {
                printer.print("no key found");
            }
            return;
        }
        throw new IllegalCommandException("Room is Dark");
    }

    private Painting getMirror(Player player) throws IllegalCommandException {
        List<RoomPart> parts = PartsManager.getAvailableParts(player);
        for (RoomPart part : parts) {
            if (part instanceof Painting) {
                return (Painting) part;
            }
        }

        throw new IllegalCommandException("there is no painting here");
    }
}
