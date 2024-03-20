package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SportsVillage extends GameLocation {
    public SportsVillage() {
        energyModifier = -30;
        locationTexture = new Texture("sports_village.jpg");
        position = new Vector2(600, 230);
        bounds.setPosition(position);
        bounds.width = 180;
        bounds.height = 101;
    }
    
}
