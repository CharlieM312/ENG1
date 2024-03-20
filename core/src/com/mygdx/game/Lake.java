package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines Lake as a GameLocation
public class Lake extends GameLocation {
    public Lake() {
        energyModifier = -20;
        hoursModifier = 1;
        locationTexture = new Texture("lake.jpg");
        interactionMenu = new Texture("lake_interaction_menu.jpg");
        position = new Vector2(410, 80);
        bounds.setPosition(position);
        bounds.width = 180;
        bounds.height = 90;
    }
}