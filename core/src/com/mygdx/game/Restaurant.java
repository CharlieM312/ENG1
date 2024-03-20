package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines Resturant as a GameLocation
public class Restaurant extends GameLocation {
    public Restaurant() {
        energyModifier = -10;
        locationTexture = new Texture("restaurant.jpg");
        position = new Vector2(250, 470);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }
}