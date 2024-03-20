package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Charles extends GameLocation{
    public Charles() {
        energyModifier = -20;
        locationTexture = new Texture("thecharles.jpg");
        position = new Vector2(400, 460);
        bounds.setPosition(position);
        bounds.width = 180;
        bounds.height = 135;
    }
    
}
