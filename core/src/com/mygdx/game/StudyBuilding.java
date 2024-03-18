package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class StudyBuilding extends GameLocation {
    public StudyBuilding() {
        this.locationTexture = new Texture("study_building.jpg");
        this.position = new Vector2(400, 280);
        this.bounds.x = 64;
        this.bounds.y = 64;
    }
}