package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class GameLocation {
    public enum State {
        IDLE,
        INTERACTING_WITH_PLAYER
    }

    protected Texture locationTexture;

    protected Vector2 position;
    // Bounding box to detect player collision
    protected Rectangle bounds;
    protected State currentState;

    public GameLocation() {
        bounds = new Rectangle();
        currentState = State.IDLE;
    }

    public Rectangle GetBounds() {
        return bounds;
    }

    public State GetCurrentState() {
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
