package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Restaurant extends GameLocation {
    public Restaurant() {
        this.locationTexture = new Texture("restaurant.jpg");
        this.position = new Vector2(250, 470);
        this.bounds.x = 64;
        this.bounds.y = 64;
    }
}