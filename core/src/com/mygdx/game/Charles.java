package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Charles extends GameLocation{
    public Charles() {
        energyModifier = -20;
        hoursModifier = 2;
        locationTexture = new Texture("thecharles.jpg");
        interactionMenu = new Texture("charles_interaction_menu.jpg");
        position = new Vector2(450, 460);
        bounds.setPosition(position);
        bounds.width = 170;
        bounds.height = 80;
    }
    
}
