package com.mygdx.game;

import javax.tools.JavaFileManager.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class House extends GameLocation {

    public House() {
        this.locationTexture = new Texture("house.jpg");
        this.position = new Vector2(168, 20);
        this.bounds.x = 64;
        this.bounds.y = 64;
    }
}