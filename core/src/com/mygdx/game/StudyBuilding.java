package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
// Defines StudyBuilding as a GameLocation
public class StudyBuilding extends GameLocation {
    public StudyBuilding() {
        energyModifier = -30;
        hoursModifier = 3;
        locationTexture = new Texture("study_building.jpg");
        interactionMenu = new Texture("study_building_interact_menu.jpg");
        position = new Vector2(400, 280);
        bounds.setPosition(position);
        bounds.width = 64;
        bounds.height = 64;
    }
}