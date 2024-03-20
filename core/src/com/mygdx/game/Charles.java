package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Charles extends GameLocation{
    public Charles() {
        energyModifier = -20;
        hoursModifier = 2;
        locationTexture = new Texture("thecharles.png");
        position = new Vector2(400, 460);
        bounds.setPosition(position);
        bounds.width = 135;
        bounds.height = 80;
    }
    
}
