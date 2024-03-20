package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SportsVillage extends GameLocation {
    public SportsVillage() {
        energyModifier = -30;
        hoursModifier = 3;
        locationTexture = new Texture("sports_village.png");
        position = new Vector2(600, 230);
        bounds.setPosition(position);
        bounds.width = 125;
        bounds.height = 100;
    }
    
}
