package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines StudyBuilding as a GameLocation
public class StudyBuilding extends GameLocation {
    public StudyBuilding() {
        locationTexture = new Texture("study_building.jpg");
        position = new Vector2(400, 280);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }
}