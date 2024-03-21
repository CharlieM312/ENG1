package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SportsVillage extends GameLocation {
    public SportsVillage() {
        energyModifier = -30;
        hoursModifier = 3;
        locationTexture = new Texture("sports_village.jpg");
        interactionMenu = new Texture("gym_interaction_menu.jpg");
        position = new Vector2(640, 230);
        bounds.setPosition(position);
        bounds.width = 180;
        bounds.height = 101;
    }
    
}
