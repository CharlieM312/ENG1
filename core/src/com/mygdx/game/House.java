package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines House as a GameLocation
public class House extends GameLocation {
    public House() {
        energyModifier = 100;
        locationTexture = new Texture("house.jpg");
        position = new Vector2(100, 280);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }
}