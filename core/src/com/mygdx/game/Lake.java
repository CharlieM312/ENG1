package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines Lake as a GameLocation
public class Lake extends GameLocation {
    public Lake() {
        locationTexture = new Texture("lake.jpg");
        position = new Vector2(700, 470);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }
}