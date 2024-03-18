package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;

public class House extends GameLocation {

    public enum HouseState {
        IDLE,
        INTERACTING
    }

    HouseState state;

    public House() {
        state = HouseState.IDLE;
        locationTexture = new Texture("house.jpg");
        position = new Vector2(100, 280);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }

    public HouseState GetState() {
        return state;
    }

    public void SetState(HouseState state) {
        this.state = state;
    }
}