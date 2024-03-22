/*
 * Base class for locations used in the game.
 * each new location should inherit from this class.
 */

package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
// Defines GameLocation as a class
public class GameLocation {
    public enum locationState {
        IDLE,
        INTERACTING_WITH_PLAYER
    }
    
    // The amount of energy that interacting with the location decreases/increases for the player
    protected int energyModifier;
    protected int hoursModifier;
    protected Texture locationTexture;
    protected Texture interactionMenu;

    protected Vector2 position;
    // Bounding box to detect player collision
    protected Rectangle bounds;
    protected locationState currentState;

    public GameLocation() {
        currentState = locationState.IDLE;
        bounds = new Rectangle();
        currentState = locationState.IDLE;
    }

    public Texture getInteractionMenu() {
        return interactionMenu;
    }

    public int GetEnergyModifier() {
        return energyModifier;
    }
    public int GethoursModifier() {
        return hoursModifier;
    }

    public Rectangle GetBounds() {
        return bounds;
    }

    public void SetCurrentState(locationState state) {
        currentState = state;
    }

    public locationState GetCurrentState() {
        return currentState;
    }

    public float GetXPosition() {
        return bounds.x;
    }

    public void SetXPosition(float x) {
        bounds.x = x;
        position.x = x;
    }

    public float GetYPosition()
    {
        return bounds.y;
    }

    public void SetYPosition(float y)
    {
        bounds.y = y;
        position.y = y;
    }
}
