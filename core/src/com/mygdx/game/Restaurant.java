package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines Resturant as a GameLocation
public class Restaurant extends GameLocation {
    public Restaurant() {
        energyModifier = -10;
        hoursModifier = 1;
        locationTexture = new Texture("restaurant.png");
        position = new Vector2(180, 470);
        bounds.setPosition(position);
        bounds.width = 136;
        bounds.height = 90;
    }
}