package com.worldnavigator.archeticture.parts.impl;

import com.worldnavigator.archeticture.parts.abstracts.LightSwitch;

public class RoomSwitch implements LightSwitch {
    private boolean state;

    public RoomSwitch() {
    }

    public RoomSwitch(boolean state) {
        this.state = state;
    }

    @Override
    public boolean hasSwitch() {
        return true;
    }

    @Override
    public void flipSwitch() {
        state = !state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
