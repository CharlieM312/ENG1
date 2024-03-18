package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Lake extends GameLocation {
    public Lake() {
        this.locationTexture = new Texture("lake.jpg");
        this.position = new Vector2(700, 470);
        this.bounds.x = 64;
        this.bounds.y = 64;
    }
}